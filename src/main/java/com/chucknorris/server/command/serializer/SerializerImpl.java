package com.chucknorris.server.command.serializer;

import com.google.gson.Gson;

public class SerializerImpl<T> implements Serializer<T> {
	private static final Gson gson = new Gson();

	@Override
	public String serialize(T objectToSerialize) {
		return gson.toJson(objectToSerialize);
	}

	@Override
	public T serialize(String jsonToDeserialize, Class classToCast) {
		return (T) gson.fromJson(jsonToDeserialize, classToCast);
	}

}
