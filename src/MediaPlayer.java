import java.io.File;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.media.Track;
import javafx.stage.Stage;

/**
 * A sample media player which loops indefinitely over the same video
 */
public class MediaPlayer extends Application {
	Stage stage; 
	private File file = new File("C:/Users/Barton/Videos/Any Video Converter/iPad/BartonStudy2_x264.mp4");
	private final String MEDIA_URL = file.toURI().toString();
	//private static final String MEDIA_URL = "C:/Users/Barton/Videos/GOPRO474.avi";

	private static String arg1;

	@Override public void start(Stage stageStart) {
		stage = stageStart;
		stage.setTitle("Media Player");

		// Create media player
		Media media = new Media((arg1 != null) ? arg1 : MEDIA_URL);
		javafx.scene.media.MediaPlayer mediaPlayer = new javafx.scene.media.MediaPlayer(media);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setCycleCount(0);

		// Print track and metadata information
		media.getTracks().addListener(new ListChangeListener<Track>() {
			public void onChanged(Change<? extends Track> change) {
				System.out.println("Track> "+change.getList());
			}
		});
		media.getMetadata().addListener(new MapChangeListener<String,Object>() {
			public void onChanged(MapChangeListener.Change<? extends String, ? extends Object> change) {
				System.out.println("Metadata> "+change.getKey()+" -> "+change.getValueAdded());
			}
		});
		
		
		

		// Add media display node to the scene graph
		MediaView mediaView = new MediaView(mediaPlayer);
		Group root = new Group();
		Scene scene = new Scene(root,640,480);
		int w = (int) scene.getWidth(); // player.getMedia().getWidth();
        int h = (int) scene.getHeight(); // player.getMedia().getHeight();
        System.out.print(w + " " +h);
        // stage.setMinWidth(w);
        // stage.setMinHeight(h);
        // make the video conform to the size of the stage now...
        mediaView.setFitHeight(h);
        mediaView.setFitWidth(w);
        

		
		root.getChildren().add(mediaView);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		if (args.length > 0) {
			arg1 = args[0];
		}
		Application.launch(args);
	}
}