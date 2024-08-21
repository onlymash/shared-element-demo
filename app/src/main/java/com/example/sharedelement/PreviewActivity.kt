package com.example.sharedelement

import android.content.Intent
import android.os.Bundle
import androidx.annotation.OptIn
import androidx.core.app.ActivityOptionsCompat
import androidx.media3.common.util.UnstableApi
import com.example.sharedelement.databinding.ActivityPreviewBinding

class PreviewActivity : BaseActivity<ActivityPreviewBinding>() {

	override val binding by lazy { ActivityPreviewBinding.inflate(layoutInflater) }

	@OptIn(UnstableApi::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding.ivPreview.setOnClickListener {
			val cls = if (binding.chipGroup.checkedChipId == R.id.chip_image_view) {
				ImageActivity::class.java
			} else {
				PlayerActivity::class.java
			}
			val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
				this,
				binding.ivPreview,
				binding.ivPreview.transitionName
			)
			startActivity(Intent(this, cls), options.toBundle())
		}
	}

}