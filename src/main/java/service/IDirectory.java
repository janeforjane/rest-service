package service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.HashMap;

public interface IDirectory {

        /**
         * Перечисление определяет тип элемента каталога.
         */
        public static enum Type {
            /**
             * Определяет каталог
             */
            DIRECTORY,
            /**
             * Определяет файл
             */
            FILE;
        }

        /**
         * Метод позволяет получить список подкаталогов и файлов заданного
         * каталога.
         * @param dirName имя каталога, содержание которого должен вернуть
         * данный метод.
         * @return коллекция типа {@link java.util.Map<> Map}, в которой
         * первый параметр содержит строку с именем файла или подкаталога.
         * Второй параметр определяет чем является элемента каталога с данным
         * именем: файлом или каталогом. Если заданный каталог пуст, то метод
         * возвращает пустую коллекцию. Если имя каталога задано неверно
         * (такого каталога нет), то метод возвращает значение null.
         */
        @GET
        @Path("{directory}")
        @Produces("text/html")
        public HashMap<String, RestService.Type> getContent(
                @PathParam("directory") String dirName);
        /**
         * Метод производит поиск файла в заданном каталоге.
         * @param dirName определяет каталог поиска.
         * @param fileName определяет шаблон имени искомого файла.
         * @return список файлов и подкаталогов заданного каталога dirName,
         * имена которых удовлетворяют заданному шаблону fileName. Первый
         * параметр коллекции (ключ)содержит строку с именем файла или
         * подкаталога. Второй параметр определяет чем является элемента
         * каталога с данным именем: файлом или каталогом. Если заданный
         * каталог пуст, то метод возвращает пустую коллекцию. Если имя то
         * каталога задано неверно (такого каталога нет), метод возвращает
         * значение null.
         */
        // find/fold?file=filename
        @GET
        @Path("find/{directory}")
        @Produces(MediaType.APPLICATION_JSON)
//        @Consumes(MediaType.APPLICATION_JSON)
        // app.json
        public HashMap<String, RestService.Type> findFile(
                @PathParam("directory") String dirName,
                @QueryParam("file") String fileName);
    }
