package br.com.geofusion.cart.application;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class CommandExecutorImpl implements CommandHandler, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public <T extends CommandResponse, R extends CommandRequest> T handle(final Class<? extends Command<T, R>> commandClass, final R request) {
		final Command<T, R> command = applicationContext.getBean(commandClass);
		return command.execute(request);
	}
}