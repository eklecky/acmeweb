package com.acme.statusmgr;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.acme.statusmgr.beans.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Controller for all web/REST requests about the status of servers
 *
 * For initial school project - just handles info about this server
 * Syntax for URLS:
 *    All start with /server
 *    /status  will give back status of server
 *    a param of 'name' specifies a requestor name to appear in response
 *
 * Examples:
 *    http://localhost:8080/server/status
 *
 *    http://localhost:8080/server/status?name=Noach
 *
 *
 *
 */
@RestController
@RequestMapping("/server")
public class StatusController {

    String template = "Server Status requested by %s";
    AtomicLong counter = new AtomicLong();


    @RequestMapping("/status")
    public ServerStatus getServerStatusInfo(@RequestParam(value="name", defaultValue="Anonymous") String name,
                                            @RequestParam(value="details", required = false) List<String> details) throws RuntimeException {

        if(details != null){
            for(String str: details){
                System.out.println("*** DEBUG INFO *** ::: " + str);
            }
        }

        return new ServerStatus(counter.incrementAndGet(),
                String.format(template, name));
    }


     @RequestMapping(" /detailed")
     @ResponseStatus(BAD_REQUEST)
    public ServerStatusInterface getDetails (@RequestParam(value="name", defaultValue="Anonymous",
             required = false) String name, @RequestParam(value="details") List<String> details) {

         ServerStatusInterface newServerStatus = new ServerStatus(counter.incrementAndGet(), String.format(template, name));


         if (details != null) {

             for (String str : details) {
                 System.out.println("***DEBUG INFO *** ::: " + str);

                 if (str.toLowerCase().equals("operations")) {
                     newServerStatus = new OperationsServerStatus(newServerStatus);
                 }
                 if (str.toLowerCase().equals("extensions")) {
                     newServerStatus = new ExtensionsServerStatus(newServerStatus);
                 }
                 if (str.toLowerCase().equals("memory")) {
                     newServerStatus = new MemoryServerStatus(newServerStatus);
                 }

                 else {

                     return (ServerStatusInterface) new ResponseEntity<String>
                             ("\"Bad Request\",\"message\":\"Required List parameter 'details' " +
                                     "is not present in request\",\"path\":\"/server/status/detailed\"",
                                     HttpStatus.BAD_REQUEST);
                 }
             }

         }

         return newServerStatus;
     }
}
