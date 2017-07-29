package org.grizz.keeper.client.http;

import com.google.gson.Gson;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class JsonCall {
    private Gson gson = new Gson();

    public JsonCallWithPayload of(Function<String, String> responseProvider) {
        return new JsonCallWithPayload(responseProvider);
    }

    public EmptyJsonCall of(Supplier<String> responseProvider) {
        return new EmptyJsonCall(responseProvider);
    }

    public class JsonCallWithPayload {
        private final Function<String, String> responseProvider;
        private Object payload;

        private JsonCallWithPayload(Function<String, String> responseProvider) {
            this.responseProvider = responseProvider;
        }

        public JsonCallWithPayload with(Object payload) {
            this.payload = payload;
            return this;
        }

        public <T> T executeWithResultAs(Class<T> clazz) {
            Objects.requireNonNull(payload, "Attached object cannot be null");

            String payloadJson = gson.toJson(payload);
            String responseJson = responseProvider.apply(payloadJson);
            return gson.fromJson(responseJson, clazz);
        }
    }

    public class EmptyJsonCall {
        private final Supplier<String> responseProvider;

        private EmptyJsonCall(Supplier<String> responseProvider) {
            this.responseProvider = responseProvider;
        }

        public <T> T executeWithResultAs(Class<T> clazz) {
            String responseJson = responseProvider.get();
            return gson.fromJson(responseJson, clazz);
        }
    }
}
