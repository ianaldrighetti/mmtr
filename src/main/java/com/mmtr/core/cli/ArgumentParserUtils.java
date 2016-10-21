package com.mmtr.core.cli;

import com.mmtr.core.AppConstants;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;

/**
 * A utility for argument handling.
 */
class ArgumentParserUtils
{
	/**
	 * Creates a new instance of an argument parser for the command-line
	 * interface.
	 *
	 * @return An argument parser.
	 */
	public ArgumentParser newInstance()
	{
		final ArgumentParser parser = ArgumentParsers.newArgumentParser(AppConstants.APP_NAME);
		parser.defaultHelp(true);
		parser.description(AppConstants.APP_NAME + " is a utility for running multi-machine powered tasks.");
		parser.epilog("You can find out more about " + AppConstants.APP_NAME +
				" by visiting " + AppConstants.APP_URL + ".");

		parser.addArgument("--mode")
				.dest("mode")
				.type(String.class)
				.choices("producer", "consumer")
				.required(true)
				.help("The mode in which this " + AppConstants.APP_NAME + " " +
						"instance runs.");

		parser.addArgument("--config")
				.dest("config")
				.type(String.class)
				.required(true)
				.metavar("file")
				.help("The path to the yml configuration file for this " +
						AppConstants.APP_NAME + " instance.");

		return parser;
	}
}
