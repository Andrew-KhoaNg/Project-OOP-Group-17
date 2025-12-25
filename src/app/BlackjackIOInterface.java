package app;

import java.io.IOException;

public interface BlackjackIOInterface {
	
	void save(String filename, String name, int money) throws IOException;
	
	BlackjackObjectloader load(String filename) throws IOException;

}
