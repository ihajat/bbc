package com.example.owner.myfruitapplication.utils;

import java.util.concurrent.Executor;

public class InstantAppExecutors extends AppExecutors {
    private static Executor instant = command -> command.run();

    public InstantAppExecutors() {
        super(instant, instant, instant);
    }
}