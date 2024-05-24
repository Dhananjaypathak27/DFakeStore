package com.inxparticle.dfakestore.view.profileScreen

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.inxparticle.dfakestore.databinding.FragmentProfileScreenBinding
import com.inxparticle.dfakestore.util.SharedPref

class ProfileScreenFragment : Fragment() {

    private lateinit var binding:FragmentProfileScreenBinding
    private val viewModel by viewModels<ProfileScreenViewModel>()
    private val CAMERA_PERMISSION_REQUEST_CODE = 100
    private val REQUEST_IMAGE_CAPTURE = 101
    lateinit var sharedPref:SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile_screen, container, false)
//        FragmentProfileScreenBindingImpl
        binding = FragmentProfileScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        sharedPref = SharedPref(requireActivity())

        binding.switch1.isChecked = sharedPref.getBoolean("nightModeOnn")


        binding.capturePhoto.setOnClickListener {
            checkCameraPermissionAndRequest()
        }                                                                

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Toast.makeText(requireContext(), "Night Mode onn", Toast.LENGTH_SHORT).show()
                sharedPref.setBoolean("nightModeOnn",true)

            } else {
                sharedPref.setBoolean("nightModeOnn",false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Toast.makeText(requireContext(), "Day Mode onn", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == CAMERA_PERMISSION_REQUEST_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(requireContext(), "permission is granted", Toast.LENGTH_SHORT).show()
            }
            else{
//                permission is denied
                Toast.makeText(requireContext(), "permission is denied", Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun checkCameraPermission():Boolean{
        val result = ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun OldRequestCameraPermission(){
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA),CAMERA_PERMISSION_REQUEST_CODE)
    }

    private fun checkCameraPermissionAndRequest(){
        if(!checkCameraPermission()){
//            OldRequestCameraPermission()
            newRequestCameraPermission()
        }
        else{
            //permission is already granted
            Toast.makeText(requireContext(), "permission is already granted", Toast.LENGTH_SHORT).show()
            dispatchTakePictureIntent()
        }
    }

    private fun newRequestCameraPermission() {
        activityResultLauncher.launch(Manifest.permission.CAMERA)
    }

    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){ isGranted ->
        if(isGranted){
            Toast.makeText(requireContext(), "permission is granted", Toast.LENGTH_SHORT).show()
            dispatchTakePictureIntent()
        }
        else{
            Toast.makeText(requireContext(), "permission is denied", Toast.LENGTH_SHORT).show()

        }

    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.capturePhotoImage.setImageBitmap(imageBitmap)
        }
    }

}