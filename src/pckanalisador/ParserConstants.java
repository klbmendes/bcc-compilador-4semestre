package pckanalisador;

public interface ParserConstants{
    int START_SYMBOL = 46;

    int FIRST_NON_TERMINAL    = 46;
    int FIRST_SEMANTIC_ACTION = 83;

    int[][] PARSER_TABLE =
    {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  2, -1, -1, -1, -1,  2, -1, -1,  1, -1, -1, -1, -1, -1,  2, -1, -1,  2, -1,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  3, -1, -1, -1, -1,  3, -1, -1, -1, -1, -1, -1, -1, -1,  3, -1, -1,  3, -1,  3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  5, -1, -1, -1, -1,  5, -1, -1, -1,  4,  4,  4, -1, -1,  5, -1, -1,  5, -1,  5, -1,  4,  4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  6, -1, -1, -1, -1,  7, -1, -1, -1, -1, -1, -1, -1, -1,  9, -1, -1, 10, -1,  8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, 11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15, 16, 15, 15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, 17, 18, -1, -1, -1, -1, -1, 20, -1, 19, -1, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, 24, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, 25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 26, -1, -1, -1, 26, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, -1, -1, -1, 27, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 30, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 32, 32, 32, 32, 32, -1, -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, 32, -1, -1, -1, -1, -1, -1, 32, 32, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, 37, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, 42, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 12, -1, -1, -1, -1, 12, 13, 13, -1, -1, -1, -1, -1, 13, 12, 13, -1, 12, 13, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 43, 43, 43, 43, 43, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, -1, 43, -1, -1, -1, 43, -1, -1, -1, -1, -1, -1, 43, 43, -1, -1, -1 },
        { -1, 44, -1, -1, -1, -1, 44, -1, -1, -1, -1, -1, -1, -1, -1, 44, -1, -1, 44, -1, 44, -1, -1, -1, -1, 44, 44, -1, -1, -1, 44, 45, 46, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 47, 47, 47, 47, 47, -1, -1, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1, -1, -1, -1, 48, -1, -1, -1, -1, -1, -1, -1, 47, -1, -1, -1, 50, -1, -1, -1, -1, -1, -1, 47, 47, -1, -1, -1 },
        { -1, 51, 51, 51, 51, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 51, 51, -1, -1, -1 },
        { -1, 52, -1, -1, -1, -1, 52, -1, -1, -1, -1, -1, -1, -1, -1, 52, -1, -1, 52, -1, 52, -1, -1, -1, -1, 52, 52, -1, -1, -1, 52, 52, 52, -1, 53, 53, 53, 53, 53, 53, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 54, 55, 56, 57, 58, 59, -1, -1, -1, -1, -1 },
        { -1, 60, 60, 60, 60, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, 60, -1, -1, -1 },
        { -1, 61, -1, -1, -1, -1, 61, -1, -1, -1, -1, -1, -1, -1, -1, 61, -1, -1, 61, -1, 61, -1, -1, -1, -1, 61, 61, -1, -1, -1, 61, 61, 61, -1, 61, 61, 61, 61, 61, 61, 62, 63, -1, -1, -1 },
        { -1, 64, 64, 64, 64, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 64, 64, -1, -1, -1 },
        { -1, 65, -1, -1, -1, -1, 65, -1, -1, -1, -1, -1, -1, -1, -1, 65, -1, -1, 65, -1, 65, -1, -1, -1, -1, 65, 65, -1, -1, -1, 65, 65, 65, -1, 65, 65, 65, 65, 65, 65, 65, 65, 66, 67, -1 },
        { -1, 68, 68, 68, 68, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 68, 68, -1, -1, -1 },
        { -1, 69, -1, -1, -1, -1, 69, -1, -1, -1, -1, -1, -1, -1, -1, 69, -1, -1, 69, -1, 69, -1, -1, -1, -1, 69, 69, -1, -1, -1, 69, 69, 69, -1, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 70 },
        { -1, 71, 72, 73, 74, 75, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 76, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 77, 78, -1, -1, -1 }
    };

    int[][] PRODUCTIONS = 
    {
        {  18, 103,  47,  48,  11, 104 },
        {  51 },
        {   0 },
        {  50,  26,  49 },
        {   0 },
        {  48 },
        {  55 },
        {  57 },
        {  61 },
        {  64 },
        {  67 },
        {  10,  54, 105,  25,  52, 106,  26,  69 },
        {   0 },
        {  54, 105,  25,  52, 106,  26,  69 },
        {   2, 107,  53 },
        {   0 },
        { 107,  27,  52 },
        {   8 },
        {   9 },
        {  17 },
        {  15 },
        {  20 },
        {  52,  56 },
        {  28,  70 },
        {  29,  70, 108 },
        {   7,  30,  58,  31 },
        {  60,   2, 109,  59 },
        {   0 },
        { 109,  27,  58 },
        {   6, 102,  97,  27 },
        {   0 },
        {  21,  30,  62,  31 },
        {  70,  97,  63 },
        {   0 },
        {  27,  62 },
        {  16,  70, 110,  48,  65, 111,  66,  11, 112 },
        {   0 },
        { 111,  12,  70, 113,  48,  65 },
        {  13,  48 },
        {   0 },
        {  19, 115,  48,  68 },
        {  23,  70, 117 },
        {  24,  70, 116 },
        {  72,  71 },
        {   0 },
        {  32,  72,  98,  71 },
        {  33,  72,  99,  71 },
        {  73 },
        {  22,  94 },
        {  14,  95 },
        {  34,  72,  96 },
        {  76,  74 },
        {   0 },
        {  75,  92,  76,  93 },
        {  35 },
        {  36 },
        {  37 },
        {  38 },
        {  39 },
        {  40 },
        {  78,  77 },
        {   0 },
        {  41,  78,  84,  77 },
        {  42,  78,  85,  77 },
        {  80,  79 },
        {   0 },
        {  43,  80,  86,  79 },
        {  44,  80,  87,  79 },
        {  82,  81 },
        {   0 },
        {  45,  82, 100,  81 },
        {   2, 114 },
        {   3,  88 },
        {   4,  89 },
        {   5, 101 },
        {   6, 102 },
        {  30,  70,  31 },
        {  41,  82,  90 },
        {  42,  82,  91 }
    };
    
        String[] PARSER_ERROR = {
                        "",
                        "esperado EOF", // "Era esperado fim de programa",
                        "esperado identificador", // "Era esperado id",
                        "esperado constante_int", // "Era esperado cte_int",
                        "esperado constante_float", // "Era esperado cte_float",
                        "esperado constante_char", // "Era esperado cte_char",
                        "esperado constante_string", // "Era esperado cte_string",
                        "esperado ask", // "Era esperado pr_ask"
                        "esperado bool", // "Era esperado pr_bool"
                        "esperado char", // "Era esperado pr_char"
                        "esperado define", // "Era esperado pr_define"
                        "esperado end", // "Era esperado pr_end"
                        "esperado elif", // "Era esperado pr_elif"
                        "esperado else", // "Era esperado pr_else"
                        "esperado false", // "Era esperado pr_false"
                        "esperado float", // "Era esperado pr_float"
                        "esperado if", // "Era esperado pr_if"
                        "esperado int", // "Era esperado pr_int"
                        "esperado main", // "Era esperado pr_main"
                        "esperado repeat", // "Era esperado pr_repeat"
                        "esperado string", // "Era esperado pr_string"
                        "esperado tell", // "Era esperado pr_tell"
                        "esperado true", // "Era esperado pr_true"
                        "esperado until", // "Era esperado pr_until"
                        "esperado while", // "Era esperado pr_while"
                        "esperado :", // "Era esperado \":\""
                        "esperado ;", // "Era esperado \";\""
                        "esperado ,", // "Era esperado \",\""
                        "esperado =", // "Era esperado \"=\""
                        "esperado <-", // "Era esperado \"<-\""
                        "esperado (", // "Era esperado \"(\""
                        "esperado )", // "Era esperado \")\""
                        "esperado &&", // "Era esperado \"&&\""
                        "esperado ||", // "Era esperado \"||\""
                        "esperado !", // "Era esperado \"!\""
                        "esperado ==", // "Era esperado \"==\""
                        "esperado !=", // "Era esperado \"!=\""
                        "esperado <", // "Era esperado \"<\""
                        "esperado <=", // "Era esperado \"<=\""
                        "esperado >", // "Era esperado \">\""
                        "esperado >=", // "Era esperado \">=\""
                        "esperado +", // "Era esperado \"+\""
                        "esperado -", // "Era esperado \"-\""
                        "esperado *", // "Era esperado \"*\""
                        "esperado /", // "Era esperado \"/\""
                        "esperado ^", // "Era esperado \"^\""

                        "esperado main", // "<main> inválido",

                        // "esperado id ask define if repeat tell", // "<opcional_main> inválido",
                        "esperado identificador ask if repeat tell", // "<opcional_main> inválido",

                        // "esperado id ask int string tell", // "<lista_instrucoes> inválido",
                        "esperado identificador ask if repeat tell", // "<lista_instrucoes> inválido",

                        // "esperado id ask end elif else int string tell until while", // //
                        // "<lista_instrucoes_> inválido",
                        "esperado identificador ask end elif else if repeat tell until while", // "<lista_instrucoes_>
                                                                                               // inválido",

                        "esperado id ask if repeat tell", // "<instrucao> inválido",
                        "esperado define", // "<dec_variaveis> inválido",
                        "esperado id", // "<lista_id> inválido",
                        "esperado ; , = <-", // "<lista_id_> inválido",
                        "esperado bool char float int string", // "<tipo> inválido",
                        "esperado id", // "<dec_atribuicao> inválido",
                        "esperado = <-", // "<dec_atribuicao_> inválido",
                        "esperado ask", // "<dec_entrada> inválido",
                        "esperado id constante_string", // "<lista_entrada> inválido",
                        "esperado , )", // "<lista_entrada_> inválido",

                        // "esperado id constante_string", // "<opcional_atribuicao> inválido",
                        "esperado identificador constante_string", // "<opcional_atribuicao> inválido",

                        "esperado tell", // "<dec_saida> inválido",
                        "esperado id constante_int constante_float constante_char constante_string false ( ! + -", // "<lista_expressao>
                                                                                                                   // inválido",
                        "esperado , )", // "<lista_expressao_> inválido",
                        "esperado if", // "<dec_selecao> inválido",

                        // "esperado elif else false", // "<elif_selecao> inválido",
                        "esperado elif else end", // "<elif_selecao> inválido",

                        // "esperado else false", // "<opcional_selecao> inválido",
                        "esperado else end", // "<opcional_selecao> inválido",

                        // "esperado repeat", // "<dec_loop> inválido",
                        "esperado identificador ask bool char float if int string repeat tell", // "<dec_loop>
                                                                                                // inválido",

                        "esperado until while", // "<dec_loop_> inválido",
                        "esperado id ask bool char float int string tell", // "<repete> inválido",
                        "esperado expressao", // "<expressao> inválido",
                        "esperado expressao", // "<expressao_> inválido",
                        "esperado expressao", // "<termo_logico> inválido",
                        "esperado expressao", // "<relacional> inválido",
                        "esperado expressao", // "<relacional_> inválido",
                        "esperado == != < <= > >=", // "<operador_relacional> inválido",
                        "esperado expressao", // "<aritmetica> inválido",
                        "esperado expressao", // "<aritmetica_> inválido",
                        "esperado expressao", // "<termo_aritmetico> inválido",
                        "esperado expressao", // "<termo_aritmetico_> inválido",
                        "esperado expressao", // "<fator> inválido",
                        "esperado expressao", // "<fator_> inválido",
                        "esperado expressao", // "<elemento> inválido"

        };
}
