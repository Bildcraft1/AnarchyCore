# Anarchy Core

Anarchy Core is a simple plugin for Anarchy type servers. It is designed to be lightweight and easy to use.

It blocks the following:
- Log4J attempts
- AntiXSS attempts (Some Minecraft Hosting providers interpreter Minecraft Logs as HTML)

Adds a simple Chat Filter for blocking chat messages containing bad words defined in the config.

## Commands
- /ac reload - Reloads the config
- /ac help - Shows the help menu
- /ac logs - Shows the logs of the plugin

## Permissions
- anarchycore.reload - Allows the user to reload the config
- anarchycore.help - Allows the user to see the help menu
- anarchycore.staff - Allows the user to see the logs of the plugin

## Config
```yaml
# Anarchy Core Config
# Created by: WhiXard
# Version: 1.0.0
blocked-words:
  - "blocked word 1"

# Anti XSS
anti-force-op: true

# Log4J
anti-log4j: true
```

## Building
- Clone the repository
- Run `mvn package`
- The plugin will be in the `target` folder

## Contributing
- Fork the repository
- Make your changes
- Create a pull request
- Wait for it to be merged
- Profit
- If you have any questions, feel free to contact me on Telegram (@WhiXard)

## License
This project is under the GNU General Public License v3.0. See the LICENSE file for more information.