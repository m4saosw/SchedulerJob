# Scheduler Job
[![Build](https://github.com/m4saosw/SchedulerJob/actions/workflows/build.yml/badge.svg)](https://github.com/m4saosw/SchedulerJob/actions/workflows/build.yml)


Simulação de agendamento de jobs dada uma janela horária.

Recebe como entrada três parâmetros e gera na saida padrão os jobs priorizados em grupos no formato json.


## Tecnologias
Tecnologias utilizadas:

    Java 8
    JUnit 4.13
	Jackson 2.11.0
	Log4j2 2.13.3
	Maven

## Testes automatizados
Para testar as funcionalidades foram criados 42 testes unitários.

## Compilação
```
$ mvn clean package
```

## Execução
```
$ java -jar  SchedulerJob-1.0.jar  dataInicio  dataFim  arquivoJson
```
**Parâmetros:**
1. dataInicio  - formato  yyyy-MM-dd HH:mm:ss
2. dataFim     - formato  yyyy-MM-dd HH:mm:ss
3. arquivoJson - nome do arquivo json

Em caso de erro nos parâmetros, uma mensagem de erro é informada no log e a execução termina com status error (1). 

**Exemplo de parâmetros:**
```
"2019-11-10 09:00:00"  "2019-11-11 12:00:00"  "C:\01-massa_prova.json"
```

## Arquivo de entrada
Array de "jobs" para execução, no qual cada posição possui um objeto com os seguintes atributos: 
1) ID: Identificação do Job;
1)	Descrição: Descrição do Job; 
2)	Data Máxima de conclusão do Job: Data máxima em que o Job deve ser concluído; 
3)	Tempo estimado: Tempo estimado de execução do Job. 

## Regras de negócio
Geração de um conjunto de arrays com as seguintes características:
1)	Cada array do conjunto representa uma lista de Jobs a serem executados em sequência;
2)	Cada array deve conter jobs que sejam executados em, no máximo, 8h; 
3)	Deve ser respeitada a data máxima de conclusão do Job; 
4)	Todos os Jobs devem ser executados dentro da janela de execução (data início e fim). 

***Observações adicionais***

Não agendar nos casos:
- Job muito longo (acima 8h);
- Data máxima de conclusão do job expirado em relação à janela;
- Job com id repetido; permanecer apenas o primeiro agendado;

## Exemplo de entrada e saída
#### Arquivo json
```
[
  {
    "ID": 1,
    "Descrição": "Importação de arquivos de fundos",
    "Data Máxima de conclusão": "2019-11-10 12:00:00",
    "Tempo estimado": 2
  },
  {
    "ID": 2,
    "Descrição": "Importação de dados da Base Legada",
    "Data Máxima de conclusão": "2019-11-11 12:00:00",
    "Tempo estimado": 4
  },
  {
    "ID": 3,
    "Descrição": "Importação de dados de integração",
    "Data Máxima de conclusão": "2019-11-11 08:00:00",
    "Tempo estimado": 6
  }
] 
```

#### Saída padrão
```
[[1,3],[2]]
```

## Configuração dos logs
***log4j2.properties***

Arquivo de definição padrão em ***/src/main/resources/log4j2.properties***

Altere o nível de log em:
```
logger.app.level = debug
```
