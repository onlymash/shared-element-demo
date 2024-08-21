package com.example.sharedelement

import android.os.Bundle
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.example.sharedelement.databinding.ActivityPlayerBinding
import okhttp3.OkHttpClient

@UnstableApi
class PlayerActivity : BaseActivity<ActivityPlayerBinding>() {

	override val binding by lazy { ActivityPlayerBinding.inflate(layoutInflater) }

	private val httpDataSourceFactory by lazy {
		OkHttpDataSource.Factory(OkHttpClient())
	}

	private val mediaSourceFactory by lazy {
		ProgressiveMediaSource.Factory(httpDataSourceFactory)
	}

	private var player: ExoPlayer? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initializePlayer()
	}

	private fun initializePlayer() {
		val player = ExoPlayer.Builder(this).setMediaSourceFactory(mediaSourceFactory).build()
		player.setMediaItem(MediaItem.fromUri(VIDEO_URL))
		binding.playerView.player = player
		player.playWhenReady = true
		player.repeatMode = ExoPlayer.REPEAT_MODE_ONE
		player.prepare()
		player.play()
		this.player = player
	}

	override fun onDestroy() {
		super.onDestroy()
		player?.release()
	}

	companion object {
		const val VIDEO_URL = "https://storage.googleapis.com/exoplayer-test-media-0/shortform_1.mp4"
	}
}