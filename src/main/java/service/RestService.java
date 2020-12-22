package service;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

@ApplicationPath("/rest")
@Path("/dirViewer")
public class RestService extends Application implements IDirectory {

    public static enum Type {
        DIRECTORY,
        FILE;
    };


    @GET
    @Path("/{dirName}")
    @Produces(MediaType.APPLICATION_JSON)

    public HashMap<String, Type> getContent(@PathParam("dirName") String dirName) {
//        File file = new File("~/");
        File file = new File("/home/jane/" + dirName);
        HashMap<String, Type> map = new HashMap<String, Type>();

        for (File f : file.listFiles()) {
            if (f.isFile()) {
                map.put(f.getName(), Type.FILE);
            }else {
                map.put(f.getName(), Type.DIRECTORY);
            }
        }

        return map;
    }

    @GET
    @Path("/find/{dirName}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap<String, Type> findFile(@PathParam("dirName") String dirName,
                                          @QueryParam("fileName") final String fileName) {

        File file = new File("/home/jane/" + dirName);
        HashMap<String, Type> map = new HashMap<String, Type>();

//        if (file.list().length>0) {
//                return null;
//        }else {

        for (File f : file.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.contains(fileName);
            }
        })) {
            if (f.isFile()) {
                map.put(f.getName(), Type.FILE);

            } else if (f.isDirectory()) {
                map.put(f.getName(), Type.DIRECTORY);
            }
        }
        return map;
//        }
    }
}
