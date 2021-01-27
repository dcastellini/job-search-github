package com.platzi.job;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;


import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.List;

public class CommanderFunctions {
    /**
     * Con esta funcion, facilitamos crear una configuracion inicial de JCommander, pidiendo el nombre del
     * programa y un Supplier de tipo T para los argumentos. Asi podemos usar alguna funcion que nos devuelva
     * un objeto que funcione como argumentos de JCommander.
     *
     * @param name              nombre que se mostrara en el CLI
     * @param argumentsSupplier una funcion que devuelva un objeto de argumentos de JCommander
     * @param <T>               Tipo que se usara para los argumentos
     * @return una instancia de {@link JCommander} ya configurada con el nombre y los argumentos.
     */
    static <T> JCommander buildCommanderWithName(String cliName, Supplier<T> argumentSupplier){
        JCommander jCommander = JCommander.newBuilder()
                .addObject(argumentSupplier.get())
                .build();

        jCommander.setProgramName(cliName);

        return jCommander;
    }
    /**
     * Funcion utilizada para tomar los datos de JCommander, los argumentos esperados y en caso de que algo falle,
     * una funcion con el JCommander que genero el error.
     */

    static Optional<List<Object>> parseArguments(
            JCommander jCommander,
            String[] arguments,
            Consumer<JCommander> onError ){

        try{
            jCommander.parse(arguments);
            return Optional.of(jCommander.getObjects());
        } catch (ParameterException paramEx){
            onError.accept(jCommander);
        }
        return Optional.empty();

    }

}
