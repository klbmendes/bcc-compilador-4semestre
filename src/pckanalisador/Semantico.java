package pckanalisador;
import java.util.Stack;


public class Semantico implements Constants {
    
   //registros semanticos
   private StringBuilder  codigoObjeto = new StringBuilder();
   private Stack<String>  pilhaTipos = new Stack<>();

   public void executeAction(int action, Token token) throws SemanticError {
      System.out.println("Acao #"+action+", Token: "+token);

      switch (action) {
         case  1:  acao1(token); break;
         case  2:  acao2(); break;
         case  3:  acao3(); break;
         case  4:  acao4(); break;
         case  5:  acao5(token); break;
         case  6:  acao6(token); break;
         case  7:  acao7(); break;
         case  8:  acao8(); break;

         case  9:  acao9(); break;  
         case 10:  acao10(); break; 

         case 11: acao11(); break;
         case 12: acao12(); break;
         case 13: acao13(); break;
         case 14: acao14(); break;
      
         case 15: acao15(); break;     
         case 16: acao16(); break;  
         case 17: acao17(); break;  
         case 18: acao18(); break;
         case 19: acao19(); break;  
      
         case 20: acao20(); break;
         case 21: acao21(); break;

         case 22: acao22(); break;     
         case 23: acao23(); break;  
         case 24: acao24(); break;  
         case 25: acao25(token); break;   
         case 26: acao26(token); break;
         case 31: acao31(token); break;   

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


   //acao 9  
   private void acao9(){   
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      pilhaTipos.push("bool");  
      codigoObjeto.append("and\n");


   }

   //acao 10
   private void acao10(){
      String tipo1 = pilhaTipos.pop();
      String tipo2 = pilhaTipos.pop();
      pilhaTipos.push("bool");  
      codigoObjeto.append("or\n")      ;
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

    //acao 15
    private void acao15(){
      pilhaTipos.pop();
      pilhaTipos.pop();
      pilhaTipos.push("bool");
      codigoObjeto.append("ceq\n");
    }

    //acao 16
    private void acao16(){
pilhaTipos.pop();
      pilhaTipos.pop();
      pilhaTipos.push("bool");
     
      codigoObjeto.append("ceq\n")      ;
   codigoObjeto.append("ldc.i4.0\n");
      codigoObjeto.append("xor\n"); 
   
   }

   //acao 17
   private void acao17() {
    pilhaTipos.pop();
    pilhaTipos.pop();
    pilhaTipos.push("bool");
      codigoObjeto.append("clt\n");
   }

   //acao 18
   private void acao18(){
      pilhaTipos.pop();
      pilhaTipos.pop();
      pilhaTipos.push("bool");
      codigoObjeto.append("cgt\n");
   }

   //acao 19
   private void acao19(){
      pilhaTipos.pop();
      pilhaTipos.pop();
      codigoObjeto.append("cgt\n");
      codigoObjeto.append("ldc.i4.0\n");
      codigoObjeto.append("xor\n");
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

//acao 22
private void acao22(){
   tipoAtual = "int64";
}   

//acao 23
private void acao23(){
   tipoAtual = "float64";
}

//acao 24
private void acao24(){
   tipoAtual = "bool";
}

//acao 25
private void acao25(Token token){
   String nome = token.getLexeme();
   codigoObjeto.append(".locals init (" + tipoAtual + " " + nome + ")\n");
}

//acao 26
private void acao26(Token token){
   String nome = token.getLexeme();
   String tipo = tipoAtual;
   pilhaTipos.push(tipo);
   codigoObjeto.append("ldloc " + nome + "\n");
}

//acao 31
private void acao31(Token token){
   String tipo = pilhaTipos.pop();
   String nome = token.getLexeme();
   codigoObjeto.append("stloc " + nome + "\n");
}

   public String getCodigoObjeto() {
      return codigoObjeto.toString();
   }
}
