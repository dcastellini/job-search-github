package com.platzi.job.cli;

import com.beust.jcommander.Parameter;

public class CLIArguments {

    public CLIArguments(){

    }
    @Parameter (
            required = true,
            descriptionKey = "KEYWORD",
            validateWith = CLIKeywordValidator.class,
            description = "KEYWORD"
    )
    private String keyword;
    @Parameter(
            names = {"--location", "-l"},
            description = "Cada búsqueda puede incluir una ubicación"

    )
    private String location;
    @Parameter(
            names = {"--page", "-p"},
            description = "La API devuelve 50 resultados, usa un numero para la pagina"
    )

    private int page = 0;
    @Parameter (
            names = {"--full-time"},
            description = "Agrega si queremos trabajos de tiempo completo."
    )
    private boolean isFullTime = false;
    @Parameter (
            names = {"--markdown"},
            description = "Obtener los resultados en markdown"
    )
    private boolean isMarkDown = false;
    @Parameter (
            names = "--help",
            validateWith = CLIHelpValidator.class,
            help = true,
            description = "Mostrar ayuda"
    )
    private boolean isHelp;

    public String getKeyword() {
        return keyword;
    }

    public String getLocation() {
        return location;
    }

    public int getPage() {
        return page;
    }

    public boolean isFullTime() {
        return isFullTime;
    }


    public boolean isMarkDown() {
        return isMarkDown;
    }


    public boolean isHelp() {
        return isHelp;
    }

    @Override
    public String toString() {
        return "CLIArguments{" +
                "keyword='" + keyword + '\'' +
                ", location='" + location + '\'' +
                ", page=" + page +
                ", isFullTime=" + isFullTime +
                ", isMarkDown=" + isMarkDown +
                ", isHelp=" + isHelp +
                '}';
    }

    public static CLIArguments newInstante(){
        return new CLIArguments();
    }



}
