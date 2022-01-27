package br.com.geofusion.cart.application;

public interface Command<T extends CommandResponse, R extends CommandRequest> {

	T execute(final R request);
}