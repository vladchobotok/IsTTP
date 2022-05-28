package utils.factory.command;

import controller.containers.CommandContainer;

public interface ContainerCreator {
    /**
     * The method creates container for commands
     *
     * @return - created container
     */
    CommandContainer createContainer();
}
