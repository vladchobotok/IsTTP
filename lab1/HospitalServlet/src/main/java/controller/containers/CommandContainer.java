package controller.containers;

import controller.commands.Command;

public interface CommandContainer {
    /**
     * The method finds and returns command for requested uri
     *
     * @param uri - uri of the command
     * @return - expected command
     */
    Command getCommand(String uri);
}
