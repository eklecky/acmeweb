package com.acme.statusmgr.beans;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

public interface ServerStatusInterface {

    public long getId();

    String getContentHeader();

    public String getStatusDesc();

}
