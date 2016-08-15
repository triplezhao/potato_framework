package potato.demo.mvp.qiqiring;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import potato.demo.chips.event.MusicEvent;

public class MusicService extends Service {

	public static String mPlayingUrl = "";

	private MediaPlayer player;


	/*public MusicService(){
		super("music");
	}
	*//**
	 * Creates an IntentService.  Invoked by your subclass's constructor.
	 *
	 *//*
	public MusicService(String name) {
		super(name);
	}*/

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.v("tag", "on bind");
		return null;
	}

//	@Override
	protected void onHandleIntent(Intent intent) {
		//action  0:play，1：pause 2：rePlay 3： player_stop
		int action = intent.getIntExtra("action",0);
		switch (action){
			case 0:
				String url = intent.getStringExtra("url");
				Log.v("action", url);
				try {
					mPlayingUrl = url;
					player.reset();
					player.setAudioStreamType(AudioManager.STREAM_MUSIC);
					player.setDataSource(url);
					player.prepare();
					player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
						@Override
						public void onPrepared(MediaPlayer mp) {
							player.start();
							EventBus.getDefault().post(new MusicEvent(0));
						}
					});
					player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mp) {
//							sendBroadcast(new Intent("br_music"));
							mPlayingUrl="";
							EventBus.getDefault().post(new MusicEvent(3));
						}

					});
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case 1:
				player.start();
				break;
			case 2:
				Log.v("tag", "pause");
				player.pause();
				break;
			case 3:
				Log.v("tag", "choice");
				player.stop();
				mPlayingUrl="";
				EventBus.getDefault().post(new MusicEvent(3));
				break;
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.v("tag", "on create");
		player = new MediaPlayer();
//		player.setAudioStreamType(AudioManager.STREAM_MUSIC);
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.v("tag", "on destroy");
		player.stop();
		player.release();
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.v("tag", "on start");

	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.v("tag", "on start command,flag"+flags+",startId"+startId);
		onHandleIntent(intent);
		return super.onStartCommand(intent, flags, startId);
	}
}
