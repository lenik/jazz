package net.bodz.bas.io.process;

import java.io.File;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class NonFinalProcessBuilder<this_t> {

    ProcessBuilder pb = new ProcessBuilder();

    public this_t command(List<String> command) {
        pb.command(command);
        return (this_t) this;
    }

    public this_t command(String... command) {
        pb.command(command);
        return (this_t) this;
    }

    public List<String> command() {
        return pb.command();
    }

    public Map<String, String> environment() {
        return pb.environment();
    }

    public File directory() {
        return pb.directory();
    }

    public this_t directory(File directory) {
        pb.directory(directory);
        return (this_t) this;
    }

    public this_t redirectInput(Redirect source) {
        pb.redirectInput(source);
        return (this_t) this;
    }

    public this_t redirectOutput(Redirect destination) {
        pb.redirectOutput(destination);
        return (this_t) this;
    }

    public this_t redirectError(Redirect destination) {
        pb.redirectError(destination);
        return (this_t) this;
    }

    public this_t redirectInput(File file) {
        pb.redirectInput(file);
        return (this_t) this;
    }

    public this_t redirectOutput(File file) {
        pb.redirectOutput(file);
        return (this_t) this;
    }

    public this_t redirectError(File file) {
        pb.redirectError(file);
        return (this_t) this;
    }

    public Redirect redirectInput() {
        return pb.redirectInput();
    }

    public Redirect redirectOutput() {
        return pb.redirectOutput();
    }

    public Redirect redirectError() {
        return pb.redirectError();
    }

    public this_t inheritIO() {
        pb.inheritIO();
        return (this_t) this;
    }

    public boolean redirectErrorStream() {
        return pb.redirectErrorStream();
    }

    public this_t redirectErrorStream(boolean redirectErrorStream) {
        pb.redirectErrorStream(redirectErrorStream);
        return (this_t) this;
    }

    public Process _start()
            throws IOException {
        return pb.start();
    }

    @Override
    public int hashCode() {
        return pb.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return pb.equals(obj);
    }

    @Override
    public String toString() {
        return pb.toString();
    }

}
