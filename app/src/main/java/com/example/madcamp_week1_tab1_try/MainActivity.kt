package com.example.madcamp_week1_tab1_try

import FragmentD
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.madcamp_week1_tab1_try.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import android.net.Uri
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity(), ImageUpdateListener {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 페이지 데이터 로드
        val list = listOf(FragmentA(), FragmentB(), FragmentC(), FragmentD())

        // 아답터 생성 및 연결
        val pagerAdapter = FragmentPagerAdapter(list, this)
        binding.viewPager.adapter = pagerAdapter

        // 탭 메뉴 제목 설정
        val titles = listOf("A", "B", "C", "D")

        // 탭 레이아웃과 뷰페이저 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()

        // 현재 활성화된 프래그먼트 추적
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                currentFragment = list[position]
            }
        })
    }

    override fun updateFragmentC(imageUri: Uri) {
        // 현재 활성화된 프래그먼트가 FragmentC인 경우에만 이미지 업데이트 수행
        (currentFragment as? FragmentC)?.updateImage(imageUri)
    }

    class FragmentPagerAdapter(
        private val fragmentList: List<Fragment>,
        fragmentActivity: AppCompatActivity
    ) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount() = fragmentList.size
        override fun createFragment(position: Int) = fragmentList[position]
    }
}

interface ImageUpdateListener {
    fun updateFragmentC(imageUri: Uri)
}
