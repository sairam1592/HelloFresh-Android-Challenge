# HelloFresh Android Challenge
 A Android challenge from Hellofresh, Germany

## Implementation Details

**Architecture**
The app follows the MVVM (Model-View-ViewModel) architecture pattern for separation of concerns and
better maintainability.

- Model: Represents the data entities and business logic.
- View: Represents the UI components and layout.
- ViewModel: Acts as a bridge between the Model and the View.

**Libraries Used**

- Retrofit: For making HTTP requests and fetching recipe data from the remote source.
- Hilt: For dependency injection to manage app-level dependencies.
- Kotlin Coroutines: For asynchronous programming and handling of background tasks.
- Material Components: For basic UI components and styling.
- Accompanist Coil: For image loading and displaying recipe images.
- Accompanist Snackbar: For displaying error messages in a Snackbar.

**Components**

- Recipe: Data class representing a recipe entity, including attributes such as name, image,
  headline, etc.
- RecipeRepository: Repository class responsible for fetching recipe data from the remote data
  source.
- RemoteDataSource: Class handling the network calls and data fetching operations using Retrofit.
- GetRecipesUseCase: Use case class responsible for fetching recipes from the repository and
  providing them to the ViewModel.
- RecipeViewModel: ViewModel class responsible for providing data to the UI and handling UI-related
  logic.
- RecipeActivity: Activity class serving as the entry point of the app and hosting the
  RecipeFragment.
- RecipeFragment: Fragment class responsible for displaying the list of recipes and handling UI
  interactions.
- RecipeItem: Composable function for displaying individual recipe items in the list view.

**UI**

- The app shows a single view consisting of a list of recipes.
- At launch, a loading spinner is displayed while fetching data from the source.
- Once the data is fetched, the loading spinner is hidden, and the recipes' view is displayed.
- The current date is shown in "dd MMM" format as the first element of the recipes list view.
- Recipes in the list are presented as cards with image, name, and headline. The recipe name is
  displayed in bold with a text size of 16sp.
- In case of an error, a Snackbar with a short description of the issue is displayed at the bottom
  of the screen.

**Testing Strategies**

- Manually verify the app behavior by launching the app on different devices/emulators, checking UI
  layout,
  loading spinner functionality, displaying recipe data, and error handling.

**How to Test**

- Clone the repository from GitHub.
- Open the project in Android Studio or your preferred IDE.
- Run the app on an emulator or physical device.
- Verify the loading spinner is displayed initially.
- Once the data is fetched, verify the recipes' view is displayed with the current date at the top.
- Ensure individual recipe items are displayed correctly with image, name, and headline.
- Trigger an error (e.g., by disconnecting the network) and verify that a Snackbar with the error
  message is displayed at the bottom of the screen.

**Conclusion**

- This README provides an overview of the Recipe App, including implementation details,
  architecture, libraries used, UI components,
  testing strategies, and instructions on how to test the app. By following these guidelines, you
  can ensure proper functionality and reliability of the app.
