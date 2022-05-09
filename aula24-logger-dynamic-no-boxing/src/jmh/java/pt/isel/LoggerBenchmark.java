package pt.isel;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;


@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class LoggerBenchmark {
    static final SavingsAccount acc = new SavingsAccount(1500, 1.25);
    static final LoggerReflect loggerReflect = new LoggerReflect(new EmptyPrinter(), MembersKind.FUNCTIONS);
    static final LoggerDynamic loggerDynamic = new LoggerDynamic(new EmptyPrinter(), MembersKind.FUNCTIONS);

    @Benchmark
    public void benchLogSavingsAccountViaReflection() {
        loggerReflect.log(acc);
    }

    @Benchmark
    public void benchLogSavingsAccountViaDynamic() {
        loggerDynamic.log(acc);
    }
}
