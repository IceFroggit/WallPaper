package icefroggit.app.presentation.adapter

import android.view.View

interface WallInteractionListener {
    fun onClickItem(data: icefroggit.app.domain.model.Data, view: View)
}