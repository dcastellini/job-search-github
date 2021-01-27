package com.platzi.job;

import com.beust.jcommander.JCommander;
import com.platzi.job.api.APIFunctions;
import com.platzi.job.api.APIjobs;
import com.platzi.job.cli.CLIArguments;
import com.platzi.job.cli.CLIFunctions;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.platzi.job.CommanderFunctions.buildCommanderWithName;
import static com.platzi.job.CommanderFunctions.parseArguments;
import static com.platzi.job.api.APIFunctions.buildAPI;

public class JobSearch {

    public static void main(String[] args) {

        JCommander jCommander = buildCommanderWithName("job-search", CLIArguments::newInstante);

        Stream<CLIArguments> streamOfCli =
                parseArguments(jCommander, args, JCommander::usage)
                .orElse(Collections.emptyList())
                .stream()
                .map(obj -> (CLIArguments) obj);

        Optional<CLIArguments> cliArgumentsOptional=
                streamOfCli.filter(cli -> !cli.isHelp())
                .filter(cli -> cli.getKeyword() != null)
                .findFirst();

        cliArgumentsOptional.map(CLIFunctions::toMap)
                .map(JobSearch::executeRequest)
                .orElse(Stream.empty())
                .forEach(System.out::println);
    }

    private static Stream<JobPosition> executeRequest(Map<String, Object> params ){
        APIjobs api = buildAPI(APIjobs.class, "https://jobs.github.com");

        return Stream.of(params)
                .map(api::jobs)
                .flatMap(Collection::stream);

    }

}
