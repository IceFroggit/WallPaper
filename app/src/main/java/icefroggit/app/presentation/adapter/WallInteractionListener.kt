package icefroggit.app.presentation.adapter

import android.view.View

interface WallInteractionListener {
    fun onCLickItem(data: icefroggit.app.domain.model.Data, view: View)
}