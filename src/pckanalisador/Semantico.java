package pckanalisador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class Semantico implements Constants {
    
   //registros semanticos
   private StringBuilder  codigoObjeto = new StringBuilder();
   private Stack<String>  pilhaTipos = new Stack<>();

   private Stack<String> pilhaRotulos = new Stack<>();
   private Map<String, String> tabelaSimbolos = new HashMap<>();
   private List<String> listaIdentificadores = new ArrayList<>();
      private int contadorRotulos = 0;
   private String operadorRelacional;  // para guardar o operador relacional em acao #9 e usar em acao #10

  private String novoRotulo() {
      return "rotulo" + (contadorRotulos++);
   }
   public void executeAction(int action, Token token) throws SemanticError {
      System.out.println("Acao #"+action+", Token: "+token);

       switch (action) {
         case 1: acao1(token); break;                 // "+" aritmetico binario
         case 2: acao2(); break;                 // "-" aritmetico binario
         case 3: acao3(); break;                 // "*" aritmetico binario
         case 4: acao4(); break;                 // "/" aritmetico binario
         case 5: acao5(token); break;            // cte_int
         case 6: acao6(token); break;            // cte_float
         case 7: acao7(); break;                 // "+" unario
         case 8: acao8(); break;                 // "-" unario
 
         case 9:  acao9(token); break;           // guarda operador_relacional
         case 10: acao10(); break;               // executa comparacao relacional
 
         case 11: acao11(); break;               // true
         case 12: acao12(); break;               // false
         case 13: acao13(); break;               // "!" (not)
         case 14: acao14(); break;               // saida (tell)
 
         case 15: acao15(); break;               // "&&" (and)
         case 16: acao16(); break;               // "||" (or)
         case 17: acao17(); break;               // "^" (potencia)
         case 18: acao18(token); break;          // cte_char
         case 19: acao19(token); break;          // cte_string
 
         case 20: acao20(); break;               // cabecalho do programa
         case 21: acao21(); break;               // fim do programa
 
         case 22: acao22(token); break;          // <tipo> reconhecido
         case 23: acao23(); break;               // fecha lista_id da declaracao -> tabela + .locals
         case 24: acao24(token); break;          // acumula identificador na lista_identificadores
         case 25: acao25(); break;               // atribuicao
         case 26: acao26(token); break;          // entrada (ask)
 
         case 27: acao27(); break;               // inicio do if
         case 28: acao28(); break;               // fecha clausula (antes de elif/else)
         case 29: acao29(); break;               // fim do if (end)
         case 30: acao30(); break;               // elif
 
         case 31: acao31(token); break;          // acesso a identificador
 
         case 32: acao32(); break;               // inicio do repeat
         case 33: acao33(); break;               // while
         case 34: acao34(); break;               // until
 
         default:
            throw new SemanticError("Acao semantica nao implementada: " + action);
      }
   }

   private void acao1(Token token) throws SemanticError {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();

       boolean tipo1Numerico = "int64".equals(tipo1) || "float64".equals(tipo1);
    boolean tipo2Numerico = "int64".equals(tipo2) || "float64".equals(tipo2);
    
    if (!tipo1Numerico || !tipo2Numerico) {
        throw new SemanticError("tipos incompatíveis em operador aritmético +", token.getPosition());
    }

      if ("int64".equals(tipo1) && "int64".equals(tipo2)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
      codigoObjeto.append("add\n");
   }

   private void acao2() {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      if ("int64".equals(tipo1) && "int64".equals(tipo2)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
      codigoObjeto.append("sub\n");
   }

   private void acao3() {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      if ("int64".equals(tipo1) && "int64".equals(tipo2)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
      codigoObjeto.append("mul\n");
   }

   private void acao4() {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      pilhaTipos.push("float64");
      codigoObjeto.append("div\n");
   }

   private void acao5(Token token) {
     pilhaTipos.push("int64");
     codigoObjeto.append("ldc.i8 " + token.getLexeme() + "\n");
     codigoObjeto.append("conv.r8\n");
   }

   private void acao6(Token token) {
      pilhaTipos.push("float64");
      codigoObjeto.append("ldc.r8 " + token.getLexeme() + "\n");
   }
   
   private void acao7() {
      String tipo = pilhaTipos.pop();
      if ("int64".equals(tipo)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }	  
   }     

   private void acao8() {
      String tipo = pilhaTipos.pop();
      if ("int64".equals(tipo)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }	
      codigoObjeto.append("ldc.i8 -1\n");
      codigoObjeto.append("conv.r8\n");
      codigoObjeto.append("mul\n");
   }


    // #9  guarda o operador relacional reconhecido para uso na acao #10
   private void acao9(Token token) {
      operadorRelacional = token.getLexeme();
   }
 
   // #10  efetua a comparacao relacional usando o operador guardado em #9
   private void acao10() {
      pilhaTipos.pop();
      pilhaTipos.pop();
      pilhaTipos.push("bool");
 
      switch (operadorRelacional) {
         case "==":
            codigoObjeto.append("ceq\n");
            break;
         case "!=":
            codigoObjeto.append("ceq\n");
            codigoObjeto.append("ldc.i4.0\n");
            codigoObjeto.append("xor\n");
            break;
         case "<":
            codigoObjeto.append("clt\n");
            break;
         case "<=":
            codigoObjeto.append("cgt\n");
            codigoObjeto.append("ldc.i4.0\n");
            codigoObjeto.append("xor\n");
            break;
         case ">":
            codigoObjeto.append("cgt\n");
            break;
         case ">=":
            codigoObjeto.append("clt\n");
            codigoObjeto.append("ldc.i4.0\n");
            codigoObjeto.append("xor\n");
            break;
      }
   }
	
   private void acao11() {
      pilhaTipos.push("bool");
      codigoObjeto.append("ldc.i4.1\n");
   }

   private void acao12() {
      pilhaTipos.push("bool");
      codigoObjeto.append("ldc.i4.0\n");
   }

   private void acao13() {
      String tipo = pilhaTipos.pop();
      pilhaTipos.push("bool");
      codigoObjeto.append("ldc.i4.1\n");
      codigoObjeto.append("xor\n");
   }

   private void acao14() {
      String tipo = pilhaTipos.pop();
      if ("int64".equals(tipo)) {
         codigoObjeto.append("conv.i8\n");
      }
      codigoObjeto.append("call void [mscorlib]System.Console::Write(" + tipo + ")\n");
    }

     // #15  "&&" (and)
   private void acao15() {
      pilhaTipos.pop();
      pilhaTipos.pop();
      pilhaTipos.push("bool");
      codigoObjeto.append("and\n");
   }
 
   // #16  "||" (or)
   private void acao16() {
      pilhaTipos.pop();
      pilhaTipos.pop();
      pilhaTipos.push("bool");
      codigoObjeto.append("or\n");
   }
 
   // #17  "^" (potencia)
   private void acao17() {
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      if ("int64".equals(tipo1) && "int64".equals(tipo2)) {
         pilhaTipos.push("int64");
      } else {
         pilhaTipos.push("float64");
      }
      codigoObjeto.append("call float64 [mscorlib]System.Math::Pow(float64, float64)\n");
   }
 
   // #18  constante_char
   private void acao18(Token token) {
      pilhaTipos.push("ldc.i4");
      codigoObjeto.append("ldstr " + token.getLexeme() + "\n");
   }
 
   // #19  constante_string
   private void acao19(Token token) {
      pilhaTipos.push("string");
      codigoObjeto.append("ldstr " + token.getLexeme() + "\n");
   }

   private void acao20() {
      codigoObjeto.append(".assembly extern mscorlib {}\n");
      codigoObjeto.append(".assembly _programa{}\n");
      codigoObjeto.append(".module _programa.exe\n");
      codigoObjeto.append("\n");
      codigoObjeto.append(".class public _unica{\n");
      codigoObjeto.append(".method static public void _principal(){\n");
      codigoObjeto.append(".entrypoint\n");
   }

   private void acao21() {
      codigoObjeto.append("ret\n");
      codigoObjeto.append("}\n");
      codigoObjeto.append("}");
   }

   
   
//registro para o tipo atual
private String tipoAtual;

// #22  guarda o tipo (token) reconhecido em <tipo>
   private void acao22(Token token) {
      String lex = token.getLexeme();
      switch (lex) {
         case "bool":   tipoAtual = "bool";    break;
         case "char":   tipoAtual = "char";    break;
         case "int":    tipoAtual = "int64";   break;
         case "float":  tipoAtual = "float64"; break;
         case "string": tipoAtual = "string";  break;
         default:       tipoAtual = lex;
      }
   }
 
   // #23  fecha a lista_id da declaracao: insere na tabela de simbolos e gera .locals
   private void acao23() {
      for (String id : listaIdentificadores) {
         tabelaSimbolos.put(id, tipoAtual);
         codigoObjeto.append(".locals(" + tipoAtual + " " + id + ")\n");
      }
      listaIdentificadores.clear();
   }
 
   // #24  acumula identificador (usado tanto em declaracao quanto em atribuicao)
   private void acao24(Token token) {
      listaIdentificadores.add(token.getLexeme());
   }

//acao 25
   private void acao25() {
      String tipoExpr = pilhaTipos.pop();
      if ("int64".equals(tipoExpr)) {
         codigoObjeto.append("conv.i8\n");
      }
      int n = listaIdentificadores.size();
      for (int i = 0; i < n - 1; i++) {
         codigoObjeto.append("dup\n");
      }
      for (String id : listaIdentificadores) {
         codigoObjeto.append("stloc " + id + "\n");
      }
      listaIdentificadores.clear();
   }
 

//acao 26
 private void acao26(Token token) throws SemanticError {
      String nome = token.getLexeme();
      String tipo = tabelaSimbolos.get(nome);
 
      if ("bool".equals(tipo) || "char".equals(tipo)) {
         throw new SemanticError(nome + " - identificador inválido para comando de entrada", token.getPosition());
      }
 
      codigoObjeto.append("call string [mscorlib]System.Console::ReadLine()\n");
      if ("int64".equals(tipo)) {
         codigoObjeto.append("call int64 [mscorlib]System.Int64::Parse(string)\n");
      } else if ("float64".equals(tipo)) {
         codigoObjeto.append("call float64 [mscorlib]System.Double::Parse(string)\n");
      }
      // string: o valor lido ja fica pronto na pilha
      codigoObjeto.append("stloc " + nome + "\n");
   }
 
  // #31
   private void acao31(Token token) {
      String nome = token.getLexeme();
      String tipo = tabelaSimbolos.get(nome);
      pilhaTipos.push(tipo);
      codigoObjeto.append("ldloc " + nome + "\n");
      if ("int64".equals(tipo)) {
         codigoObjeto.append("conv.r8\n");
      }
   }
 
 
   // #27  inicio do if
   private void acao27() {
      pilhaTipos.pop();
      String rotuloFimIf = novoRotulo();   // rotulo da 1a instrucao apos o end
      String rotuloProx = novoRotulo();    // rotulo do proximo elif/else
      pilhaRotulos.push(rotuloFimIf);
      codigoObjeto.append("brfalse " + rotuloProx + "\n");
      pilhaRotulos.push(rotuloProx);
   }

 // #28  fecha uma clausula (antes de elif/else/end)
   private void acao28() {
      String rotuloDesempilhado1 = pilhaRotulos.pop();
      String rotuloDesempilhado2 = pilhaRotulos.pop();
      codigoObjeto.append("br " + rotuloDesempilhado2 + "\n");
      pilhaRotulos.push(rotuloDesempilhado2);
      codigoObjeto.append(rotuloDesempilhado1 + ":\n");
   }
 
   // #29  fim do if (end)
   private void acao29() {
      String rotulo = pilhaRotulos.pop();
      codigoObjeto.append(rotulo + ":\n");
   }
 
   // #30  elif
   private void acao30() {
      pilhaTipos.pop();
      String rotulo = novoRotulo();
      codigoObjeto.append("brfalse " + rotulo + "\n");
      pilhaRotulos.push(rotulo);
   }

// #32  inicio do repeat
   private void acao32() {
      String rotulo = novoRotulo();
      codigoObjeto.append(rotulo + ":\n");
      pilhaRotulos.push(rotulo);
   }
 
   // #33  while
   private void acao33() {
      pilhaTipos.pop();
      String rotulo = pilhaRotulos.pop();
      codigoObjeto.append("brtrue " + rotulo + "\n");
   }
 
   // #34  until
   private void acao34() {
      pilhaTipos.pop();
      String rotulo = pilhaRotulos.pop();
      codigoObjeto.append("brfalse " + rotulo + "\n");
   }

   public String getCodigoObjeto() {
      return codigoObjeto.toString();
   }
}
