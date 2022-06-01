package com.example.mvvmrecipes.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mvvmrecipes.BaseApplication
import com.example.mvvmrecipes.R
import com.example.mvvmrecipes.ui.theme.AppTheme
import com.example.mvvmrecipes.ui.theme.view.RecipeList
import com.example.mvvmrecipes.ui.theme.view.SearchAppBar
import com.example.mvvmrecipes.ui.theme.view.SnackBarController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication
    private val snackBarController = SnackBarController(lifecycleScope)
    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipe.value

                val query = viewModel.query.value

                val selectedCategory = viewModel.selectedCategory.value

                val loading = viewModel.loading.value

                val page = viewModel.page.value

                val scaffoldState = rememberScaffoldState()

                AppTheme(
                    darkTheme = loading,
                    displayProgressBar = application.isDark.value,
                    scaffoldState = scaffoldState
                ) {
                    Scaffold(
                        topBar = {
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = {
                                    if (viewModel.selectedCategory.value?.value == "Milk") {
                                        snackBarController.getScope().launch {
                                            snackBarController.showSnackBar(
                                                scaffoldState = scaffoldState,
                                                message = "Invalid category: Milk",
                                                actionLabel = "Hide"
                                            )
                                        }
                                    } else {
                                        viewModel.onTriggerEvent(RecipeListEvent.NewSearchEvent)
                                    }
                                },
                                categories = getAllFoodCategories(),
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onToggleTheme = application::toggleLightTheme
                            )
                        },
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ) {
                        RecipeList(
                            loading = loading,
                            recipes = recipes,
                            onChangeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                            page = page,
                            onTriggerNextPage = {
                                viewModel.onTriggerEvent(RecipeListEvent.NextPageEvent)
                            },
                            onNavigateToRecipeDetailScreen = {
                                val bundle = Bundle()
                                bundle.putInt("recipeId", it)
                                findNavController().navigate(R.id.action_recipeListFragment_to_recipeFragment, bundle)
                            }
                        )
                    }
                }
            }
        }
    }
}