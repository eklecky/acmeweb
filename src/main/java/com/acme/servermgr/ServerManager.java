package com.acme.servermgr;

/**
 * Manage all servers (service providers) being tracked by the Acme server tracking system
 * For now just some simple static methods for use in school project.
 * Treat this as a 'black box' that gives back indicators like up, running etc for
 * various 'services' that are being managed.
 */
public class ServerManager {

    /**
     * ServerManager provides the decorators with the string status message desired.
     * When a decorator calls a method of this class, that method returns a string for that decorator's status.
     */
    static public String getCurrentServerStatus() {
        return "up";  // The server is up
    }

    static public String getOperations() {
        return "operating normally";// operating normally
    }

    static public String getExtensions() {
        return "- [Hypervisor, Kubernetes, RAID-6]"; // extensions used
    }

    static public String getMemoryStatus() {
        return "memory is running low"; // memory running low
    }


    /**
     * Find out if this server is operating normally
     *
     * @return Boolean indicating if server is operating normally
     */
    static public Boolean isOperatingNormally() {
        System.out.println("operating normally")
        ;
        return true;
    }
}
