package br.com.geofusion.cart.application;

public interface CommandHandler {

	<T extends CommandResponse, R extends CommandRequest> T handle(Class<? extends Command<T, R>> commandClass, R request);
}
