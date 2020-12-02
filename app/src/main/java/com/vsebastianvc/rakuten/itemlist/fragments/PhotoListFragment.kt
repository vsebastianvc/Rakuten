package com.vsebastianvc.rakuten.itemlist.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.vsebastianvc.rakuten.BuildConfig
import com.vsebastianvc.rakuten.R
import com.vsebastianvc.rakuten.customviews.QuickReturnListView
import com.vsebastianvc.rakuten.databinding.FragmentPhotoListBinding
import com.vsebastianvc.rakuten.itemlist.adapters.CustomPhotosAdapter
import com.vsebastianvc.rakuten.itemlist.viewmodel.ItemListViewModel
import com.vsebastianvc.rakuten.models.Photo
import com.vsebastianvc.rakuten.utils.PHOTO_TITLE
import com.vsebastianvc.rakuten.utils.getNavigationResultLiveData
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.min


/**
 * Created by Sebastian on 11/29/2020.
 */
class PhotoListFragment : Fragment() {
    private var TAG = PhotoListFragment::class.java.simpleName
    private lateinit var customPhotosAdapter: CustomPhotosAdapter
    private lateinit var binding: FragmentPhotoListBinding
    private val viewModel: ItemListViewModel by viewModel()

    private lateinit var listView: QuickReturnListView
    private lateinit var quickReturnView: TextView
    private var quickReturnViewHeight: Int = 0
    private var cachedVerticalScrollRange: Int = 0
    private lateinit var header: View
    lateinit var placeHolder: View

    private val stateOnScreen = 0
    private val stateOffScreen = 1
    private val stateReturning = 2
    private var state = stateOnScreen
    private var scrollY = 0
    private var minRawY = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate<FragmentPhotoListBinding>(
                inflater, R.layout.fragment_photo_list, container, false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        header = inflater.inflate(R.layout.header, container, false)
        placeHolder = header.rootView.findViewById(R.id.placeholder)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customPhotosAdapter = CustomPhotosAdapter(requireContext(), listOf())
        binding.lvPhotos.adapter = customPhotosAdapter
        viewModel.updatePhotoList()
        viewModel.photoPageLiveData.observe(viewLifecycleOwner, {
            customPhotosAdapter.refreshData(it.photos.photo)
            handleQuickReturnPattern()
        })
        viewModel.handleResponseErrors.observe(viewLifecycleOwner, {
            if (BuildConfig.DEBUG)
                Log.d(TAG, "Error response -> $it")

            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            builder.setTitle(resources.getString(R.string.error))
            builder.setMessage(resources.getString(R.string.oops))
            builder.setNeutralButton(resources.getString(R.string.ok)) { dialog, _ -> dialog.dismiss() }
            builder.create().show()
        })


        binding.lvPhotos.setOnItemClickListener { _, v, position, _ ->
            val photo = listView.getItemAtPosition(position)
            navigateToDetailsScreen(v, photo as Photo)
        }
        this.getNavigationResultLiveData<String>(PHOTO_TITLE)?.observe(viewLifecycleOwner, {
            binding.tvTitle.text = it
        })
    }

    private fun handleQuickReturnPattern() {
        binding.header.visibility = View.VISIBLE
        listView = binding.lvPhotos
        quickReturnView = binding.header
        listView.addHeaderView(header)
        listView.viewTreeObserver.addOnGlobalLayoutListener {
            quickReturnViewHeight = quickReturnView.height
            listView.computeScrollY()
            cachedVerticalScrollRange = listView.getListHeight()
        }

        listView.setOnScrollListener(object : AbsListView.OnScrollListener {
            @SuppressLint("NewApi")
            override fun onScroll(view: AbsListView?, firstVisibleItem: Int,
                                  visibleItemCount: Int, totalItemCount: Int) {
                scrollY = 0
                var translationY = 0
                if (listView.scrollYIsComputed()) {
                    scrollY = listView.getComputedScrollY()
                }
                val rawY: Int = (placeHolder.top
                        - min(
                        cachedVerticalScrollRange
                                - listView.height, scrollY))
                when (state) {
                    stateOffScreen -> {
                        if (rawY <= minRawY) {
                            minRawY = rawY
                        } else {
                            state = stateReturning
                        }
                        translationY = rawY
                    }
                    stateOnScreen -> {
                        if (rawY < -quickReturnViewHeight) {
                            state = stateOffScreen
                            minRawY = rawY
                        }
                        translationY = rawY
                    }
                    stateReturning -> {
                        translationY = rawY - minRawY - quickReturnViewHeight
                        if (translationY > 0) {
                            translationY = 0
                            minRawY = rawY - quickReturnViewHeight
                        }
                        if (rawY > 0) {
                            state = stateOnScreen
                            translationY = rawY
                        }
                        if (translationY < -quickReturnViewHeight) {
                            state = stateOffScreen
                            minRawY = rawY
                        }
                    }
                }
                quickReturnView.translationY = translationY.toFloat()
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {}
        })
    }

    private fun navigateToDetailsScreen(view: View, photo: Photo) {
        val direction = PhotoListFragmentDirections.actionPhotoListFragmentToPhotoDetailsFragment(
                photo
        )
        view.findNavController().navigate(direction)
    }
}