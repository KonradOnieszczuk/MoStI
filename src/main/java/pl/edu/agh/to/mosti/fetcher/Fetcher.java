package pl.edu.agh.to.mosti.fetcher;

import java.io.IOException;

abstract class Fetcher {

    public abstract void fetch(PageData pageData) throws IOException, InterruptedException;
}
