package GiaoDienKhachHang;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class YouTubePlayerDialog extends JDialog {

    private final String youtubeVideoUrl;
    private final JFXPanel fxPanel = new JFXPanel();

    public YouTubePlayerDialog(Frame owner, String title, String youtubeVideoUrl) {
        super(owner, title, true);
        this.youtubeVideoUrl = youtubeVideoUrl;

        initUI();
    }
    private void initUI() {
        setLayout(new BorderLayout());
        add(fxPanel, BorderLayout.CENTER);

        Platform.runLater(this::createScene);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(640, 480);
        setLocationRelativeTo(null);
    }

    private void createScene() {
        WebView webView = new WebView();
        fxPanel.setScene(new Scene(webView));

       //Gọi loadYouTubeVideo trên luồng JavaFX chính
    Platform.runLater(() -> loadYouTubeVideo(webView));
    }

    private void loadYouTubeVideo(WebView webView) {
        try {
        URL url = new URL(youtubeVideoUrl);
        String html = "<iframe width='640' height='360' src='" + url + "' frameborder='0' allowfullscreen></iframe>";
        webView.getEngine().loadContent(html);
        
    } catch (MalformedURLException e) {
        e.printStackTrace();
    }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String youtubeVideoUrl = "https://www.youtube.com/watch?v=Y_GoCyiY6K8s";
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton playButton = new JButton("Play Video");
            playButton.addActionListener(e -> {
                YouTubePlayerDialog dialog = new YouTubePlayerDialog(frame, "YouTube Player", youtubeVideoUrl);
                dialog.setVisible(true);
            });

            frame.add(playButton);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        
    }
}
