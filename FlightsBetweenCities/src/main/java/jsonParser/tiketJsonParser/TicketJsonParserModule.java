package jsonParser.tiketJsonParser;

import com.google.gson.Gson;
import jsonParser.tiketJsonParser.TicketHolder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TicketJsonParserModule {
    public static TicketHolder parse(String path) throws IOException {
        Gson gson = new Gson();
        String json = FileUtils.readFileToString(new File(path),
                StandardCharsets.UTF_8);
        TicketHolder ticketHolder = gson.fromJson(json, TicketHolder.class);
        return ticketHolder;
    }
}
