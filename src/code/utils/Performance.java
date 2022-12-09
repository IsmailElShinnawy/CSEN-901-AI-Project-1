package code.utils;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

public class Performance {
  public static void computeRunTimeAndMemory() {

    OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    // getProcessCpuTime returns the CPU time used by the process on which the Java
    // virtual machine is running in nanoseconds
    Runtime runtime = Runtime.getRuntime();
    double mb = 1024 * 1024;
    // Java Garbage Collection is the process by which Java programs perform
    // automatic memory management
    // Java Garbage Collection is the process of reclaiming the runtime unused
    // memory by destroying the unused objects
    runtime.gc();
    double memoryUsedBytes = runtime.totalMemory() - runtime.freeMemory();
    double memoryUsedMBytes = Math.round(memoryUsedBytes / mb * 100.0) / 100.0;
    long cpuTimeMs = osBean.getProcessCpuTime() / 1000000;
    System.out.println("CPU time in milliseconds is: " + cpuTimeMs);
    System.out.println("Memory used is " + memoryUsedMBytes + " in Mega Bytes");
  }
}
