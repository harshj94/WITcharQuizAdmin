package witchar.witcharquizadmin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Demo");
        query2.getInBackground("QNGkwKjpjt", new GetCallback<ParseObject>() {
            public void done(ParseObject gameScore, ParseException e) {
                if (e == null) {
                    gameScore.put("Demo", "Demo");
                    gameScore.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Demo");
                            query1.getInBackground("QNGkwKjpjt", new GetCallback<ParseObject>() {
                                public void done(ParseObject object, ParseException e) {
                                    Date d = null;
                                    if (e == null) {
                                        d = object.getUpdatedAt();
                                    }
                                    if (d != null) {
                                        Toast.makeText(getApplicationContext(), d.toString(), Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}