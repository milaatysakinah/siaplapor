package com.app.siaplapor.data.session;

public interface SessionRepository<T> {
    public final String SHARED_PREFERENCE_NAME = "SessionSharedPreferences";

    T initialize(T sessionData);
    T getSessionData();
    void setSessionData(T sessionData);
    void destroy();
    void update(T sessionData);
}
