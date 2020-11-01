package br.com.massao.test.schedulerjob.v1.helper;

/**
 * Conteudo dos arquivos Json
 */
public class JsonInput {

    public static final String FILE_01_MASSA_PROVA = "[\n" +
            "  {\n" +
            "    \"ID\": 1,\n" +
            "    \"Descrição\": \"Importação de arquivos de fundos\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
            "    \"Tempo estimado\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 2,\n" +
            "    \"Descrição\": \"Importação de dados da Base Legada\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 12:00:00\",\n" +
            "    \"Tempo estimado\": 4\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 3,\n" +
            "    \"Descrição\": \"Importação de dados de integração\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 08:00:00\",\n" +
            "    \"Tempo estimado\": 6\n" +
            "  }\n" +
            "]";

    public static final String FILE_02_VAZIO = "";

    public static final String FILE_03_INVALIDO = "conteudo invalido";

    public static final String FILE_04_IDS_DUPLICADOS = "[\n" +
            "  {\n" +
            "    \"ID\": 2,\n" +
            "    \"Descrição\": \"Importação de arquivos de fundos\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
            "    \"Tempo estimado\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 2,\n" +
            "    \"Descrição\": \"Descartar esse pois id esta duplicado\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 12:00:00\",\n" +
            "    \"Tempo estimado\": 4\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 1,\n" +
            "    \"Descrição\": \"Importação de dados de integração\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 08:00:00\",\n" +
            "    \"Tempo estimado\": 6\n" +
            "  }\n" +
            "]";

    public static final String FILE_05_EXPIRADO_VALIDO_POSTERIOR = "[\n" +
            "  {\n" +
            "    \"ID\": 1,\n" +
            "    \"Descrição\": \"Posterior\",\n" +
            "    \"Data Máxima de conclusão\": \"2021-03-10 12:00:00\",\n" +
            "    \"Tempo estimado\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 2,\n" +
            "    \"Descrição\": \"Importação de dados da Base Legada\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 12:00:00\",\n" +
            "    \"Tempo estimado\": 4\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 3,\n" +
            "    \"Descrição\": \"Expirado\",\n" +
            "    \"Data Máxima de conclusão\": \"2000-05-11 08:00:00\",\n" +
            "    \"Tempo estimado\": 6\n" +
            "  }\n" +
            "]";

    public static final String FILE_06_MINHA_TABELA = "[{\n" +
            "        \"ID\": 1,\n" +
            "        \"Descrição\": \"Importação de arquivos de fundos\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
            "        \"Tempo estimado\": 2\n" +
            "    }, {\n" +
            "        \"ID\": 2,\n" +
            "        \"Descrição\": \"Importação de dados da Base Legada\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-20 13:00:00\",\n" +
            "        \"Tempo estimado\": 4\n" +
            "    }, {\n" +
            "        \"ID\": 3,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 14:00:00\",\n" +
            "        \"Tempo estimado\": 7\n" +
            "    }, {\n" +
            "        \"ID\": 4,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 15:00:00\",\n" +
            "        \"Tempo estimado\": 4\n" +
            "    }, {\n" +
            "        \"ID\": 5,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2020-05-20 16:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 6,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 17:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 7,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 18:00:00\",\n" +
            "        \"Tempo estimado\": 2\n" +
            "    }, {\n" +
            "        \"ID\": 8,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 19:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 9,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 20:00:00\",\n" +
            "        \"Tempo estimado\": 2\n" +
            "    }\n" +
            "]";

    public static final String FILE_07_MINHA_TABELA_MESMA_LINHA = "[{\"ID\":1,\"Descrição\":\"Importação de arquivos de fundos\",\"Data Máxima de conclusão\":\"2019-11-10 12:00:00\",\"Tempo estimado\":2},{\"ID\":2,\"Descrição\":\"Importação de dados da Base Legada\",\"Data Máxima de conclusão\":\"2019-11-20 13:00:00\",\"Tempo estimado\":4},{\"ID\":3,\"Descrição\":\"Importação de dados de integração\",\"Data Máxima de conclusão\":\"2019-11-10 14:00:00\",\"Tempo estimado\":7},{\"ID\":4,\"Descrição\":\"Importação de dados de integração\",\"Data Máxima de conclusão\":\"2019-11-10 15:00:00\",\"Tempo estimado\":4},{\"ID\":5,\"Descrição\":\"Importação de dados de integração\",\"Data Máxima de conclusão\":\"2019-11-20 16:00:00\",\"Tempo estimado\":1},{\"ID\":6,\"Descrição\":\"Importação de dados de integração\",\"Data Máxima de conclusão\":\"2019-11-10 17:00:00\",\"Tempo estimado\":1},{\"ID\":7,\"Descrição\":\"Importação de dados de integração\",\"Data Máxima de conclusão\":\"2019-11-10 18:00:00\",\"Tempo estimado\":2},{\"ID\":8,\"Descrição\":\"Importação de dados de integração\",\"Data Máxima de conclusão\":\"2019-11-10 19:00:00\",\"Tempo estimado\":1},{\"ID\":9,\"Descrição\":\"Importação de dados de integração\",\"Data Máxima de conclusão\":\"2019-11-10 20:00:00\",\"Tempo estimado\":2}]";

    public static final String FILE_08_MINHA_OUTRA_TABELA_DESCENDENTE = "[\t{\n" +
            "        \"ID\": 1,\n" +
            "        \"Descrição\": \"Descartar - job com tempo estimado mais de 8 h\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 23:00:00\",\n" +
            "        \"Tempo estimado\": 20\n" +
            "    }, \n" +
            "\t{\n" +
            "        \"ID\": 2,\n" +
            "        \"Descrição\": \"Importação de arquivos de fundos\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 22:30:00\",\n" +
            "        \"Tempo estimado\": 0.6\n" +
            "    }, \n" +
            "\t{\n" +
            "        \"ID\": 3,\n" +
            "        \"Descrição\": \"Importação de arquivos de fundos - valido\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 21:00:00\",\n" +
            "        \"Tempo estimado\": 0.5\n" +
            "    }, {\n" +
            "        \"ID\": 4,\n" +
            "        \"Descrição\": \"Importação de dados da Base Legada\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 19:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 5,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 18:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 6,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 17:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 7,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 16:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 8,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 15:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 9,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 14:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 10,\n" +
            "        \"Descrição\": \"Importação de dados de integração\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 13:00:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "    }, {\n" +
            "        \"ID\": 11,\n" +
            "        \"Descrição\": \"Job ocupa todo o limite 8h - executar sozinho\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
            "        \"Tempo estimado\": 8\n" +
            "    }, {\n" +
            "        \"ID\": 3,\n" +
            "        \"Descrição\": \"Descartar este job id 3 duplicado\",\n" +
            "        \"Data Máxima de conclusão\": \"2019-11-10 23:59:00\",\n" +
            "        \"Tempo estimado\": 1\n" +
            "\t}\n" +
            "]";

    public static final String FILE_09_VALIDOS_E_INVALIDOS = "[\n" +
            "  {\n" +
            "    \"ID\": 2,\n" +
            "    \"Descrição\": \"Importação de arquivos de fundos\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
            "    \"Tempo estimado\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 2,\n" +
            "    \"Descrição\": \"Tempo estimado invalido\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 12:00:00\",\n" +
            "    \"Tempo estimado\": aaaaaaaaa\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 1,\n" +
            "    \"Descrição\": \"Importação de dados de integração\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 08:00:00\",\n" +
            "    \"Tempo estimado\": 6\n" +
            "  }\n" +
            "]";


    public static final String FILE_10_VALIDO_EXPIRADO_ACIMAHORAS = "[\n" +
            "  {\n" +
            "    \"ID\": 1,\n" +
            "    \"Descrição\": \"Posterior a janela execucao\",\n" +
            "    \"Data Máxima de conclusão\": \"2021-03-10 12:00:00\",\n" +
            "    \"Tempo estimado\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 2,\n" +
            "    \"Descrição\": \"Importação de dados da Base Legada\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 12:00:00\",\n" +
            "    \"Tempo estimado\": 4\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 3,\n" +
            "    \"Descrição\": \"Expirado em relacao a janela execucao\",\n" +
            "    \"Data Máxima de conclusão\": \"2000-05-11 08:00:00\",\n" +
            "    \"Tempo estimado\": 6\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 4,\n" +
            "    \"Descrição\": \"Igual 8 horas tempo estimado\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 08:00:00\",\n" +
            "    \"Tempo estimado\": 8\n" +
            "  },\n" +
            "  {\n" +
            "    \"ID\": 5,\n" +
            "    \"Descrição\": \"Acima de 8 horas tempo estimado\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-11 08:00:00\",\n" +
            "    \"Tempo estimado\": 10\n" +
            "  }  \n" +
            "]";

    public static final String FILE_11_UM_JOB_VALIDO = "[\n" +
            "  {\n" +
            "    \"ID\": 1,\n" +
            "    \"Descrição\": \"Importação de arquivos de fundos\",\n" +
            "    \"Data Máxima de conclusão\": \"2019-11-10 12:00:00\",\n" +
            "    \"Tempo estimado\": 2\n" +
            "  }\n" +
            "]";
}
