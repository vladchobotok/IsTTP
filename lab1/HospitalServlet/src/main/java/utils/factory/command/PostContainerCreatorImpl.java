package utils.factory.command;

import controller.containers.CommandContainer;
import controller.containers.PostCommandContainerImpl;

public class PostContainerCreatorImpl implements ContainerCreator{
    @Override
    public CommandContainer createContainer() {
        return new PostCommandContainerImpl();
    }
}
