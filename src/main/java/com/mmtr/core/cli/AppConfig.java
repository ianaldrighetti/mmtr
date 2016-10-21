package com.mmtr.core.cli;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.mmtr.core.excp.AppConfigException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

/**
 * The application configuration.
 */
public class AppConfig
{
	/**
	 * Parses the configuration file into an instance of {@link AppConfig}.
	 *
	 * @param path The path to the yml configuration file.
	 * @return An {@link AppConfig}.
	 * @throws FileNotFoundException if the file is not found.
	 * @throws YamlException if the file contains a YAML issue.
	 * @throws AppConfigException if the file contains configuration errors.
	 */
	public static AppConfig parse(final String path) throws FileNotFoundException, YamlException, AppConfigException
	{
		if (path == null) {
			throw new IllegalArgumentException("The path is required.");
		}

		final File file = new File(path);
		if (!file.exists()) {
			throw new FileNotFoundException("The file " + file
					.getAbsolutePath() + " does not exist.");
		}
		else if (!file.isFile()) {
			throw new IllegalArgumentException("The path " + file
					.getAbsolutePath() + " does not resolve to a file.");
		}

		return new AppConfig(file);
	}

	private File homeDir;

	/**
	 * Initializes an {@link AppConfig}.
	 *
	 * @param path The path to the yml configuration file.
	 * @throws FileNotFoundException if the file is not found.
	 * @throws YamlException if the file contains a YAML issue.
	 * @throws AppConfigException if the file contains configuration errors.
	 */
	private AppConfig(final File path) throws FileNotFoundException, YamlException, AppConfigException
	{
		final YamlReader reader = new YamlReader(new FileReader(path));
		load(reader);
	}

	/**
	 * Loads the configuration from the {@link YamlReader}.
	 *
	 * @param reader The YamlReader that contains the file.
	 * @throws YamlException if the file contains a YAML issue.
	 * @throws AppConfigException if the file contains configuration errors.
	 */
	private void load(final YamlReader reader) throws YamlException, AppConfigException
	{
		final Map config = reader.read(Map.class);

		setHomeDir((String) config.get("home"));
	}

	/**
	 * Returns the {@link File} representing the path to where the producer does
	 * all of it's work -- along with where consumers are assigned their work.
	 *
	 * @return The path to the producer's home.
	 */
	public File getHomeDir()
	{
		return homeDir;
	}

	/**
	 * Validates and sets the home directory path.
	 *
	 * @param path The path to the home directory.
	 * @throws AppConfigException if the path is invalid or could not be
	 * created.
	 */
	private void setHomeDir(final String path) throws AppConfigException
	{
		if (path == null) {
			throw new AppConfigException("The home property which indicates " +
					"where the home of the producer works is required.",
					"home");
		}

		final File dir = new File(path);
		if (dir.exists() && !dir.isDirectory()) {
			throw new AppConfigException("The home property must be a path to" +
					" a directory.", "home");
		}
		else if (!dir.exists() && !dir.mkdirs()) {
			throw new AppConfigException("The path " + dir.getAbsolutePath()
					+ " does not exist and could not be created.", "home");
		}

		this.homeDir = dir;
	}
}
