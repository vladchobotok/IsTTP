package utils.factory.command;

import controller.containers.CommandContainer;
import controller.containers.GetCommandContainerImpl;

public class GetContainerCreatorImpl implements ContainerCreator{
    @Override
    public CommandContainer createContainer() {
        return new GetCommandContainerImpl();
    }
}
