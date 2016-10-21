package com.mmtr.core.cli;

import com.esotericsoftware.yamlbeans.YamlException;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.mmtr.core.excp.AppConfigException;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;

/**
 * The entry point to the cli.
 */
public class Program
{
	/**
	 * The entry point to mmtr through the command-line.
	 *
	 * @param args The arguments, pass --help for help.
	 */
	public static void main(String... args)
	{
		try {
			execute(args);
		}
		catch (final Exception e) {
			System.err.println("An error occurred which caused an unexpected end of execution.");
			e.printStackTrace();
		}
	}

	/**
	 * Begins the execution of the program.
	 *
	 * @param args The arguments.
	 * @throws Exception Any exception thrown is one that will cause abrupt
	 * termination of the program. These are due to invalid arguments.
	 */
	private static void execute(final String... args) throws Exception
	{
		Injector injector = Guice.createInjector(new CliModule());

		final Program program = injector.getInstance(Program.class);
		program.run(args);
	}

	@Inject
	private ArgumentParserUtils argumentParserUtils;

	/**
	 * The program (surprise!).
	 */
	public Program()
	{
	}

	/**
	 * Runs the program with the specified arguments.
	 *
	 * @param args The arguments.
	 * @throws FileNotFoundException If the configuration file or any other
	 * specified file within the command-line arguments is not found.
	 */
	private void run(final String... args) throws FileNotFoundException, YamlException, AppConfigException
	{
		final ArgumentParser parser = argumentParserUtils.newInstance();

		try {
			Namespace result = parser.parseArgs(args);

			final String mode = result.get("mode");
			final String config = result.get("config");

			start(mode, config);
		}
		catch (final ArgumentParserException e) {
			parser.handleError(e);
		}
	}

	/**
	 * Starts the execution of the program based on the configuration.
	 *
	 * @param mode The mode in which the program should run, e.g. producer or
	 *                consumer.
	 * @param config The path to the configuration yml file.
	 * @throws FileNotFoundException If a file was not found.
	 */
	private void start(final String mode, final String config) throws FileNotFoundException, YamlException, AppConfigException
	{
		final AppConfig appConfig = AppConfig.parse(config);
	}
}
