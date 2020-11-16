package com.acme.statusmgr.beans;


public abstract class ServerStatusDecorator implements ServerStatusInterface {
    protected ServerStatusInterface tempServer;

    public ServerStatusDecorator(ServerStatusInterface newServerStatus) {
        tempServer = newServerStatus;
    }

    public ServerStatusDecorator() {

    }

    public long getId() {
        return tempServer.getId();
    }

    public String getStatusDesc() {
        return tempServer.getStatusDesc();
    }

    static public Boolean isOperatingNormally()
    {
        return true;
    }


    public String getContentHeader(){
        return tempServer.getContentHeader();
    }


    //public String getOperation(){
    //    return tempServer.
    //}

}
