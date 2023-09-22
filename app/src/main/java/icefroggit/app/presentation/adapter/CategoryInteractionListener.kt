package icefroggit.app.presentation.adapter

import android.view.View
import icefroggit.app.domain.model.Category

interface CategoryInteractionListener {
    fun onClickCategory(category : Category, view: View)
}