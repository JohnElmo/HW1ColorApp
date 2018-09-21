**Link to GitHub repository: ** https://github.com/JohnElmo/HW1ColorApp

**App Explanation:**

This app is developed for android os and coded in the java language. This is a simple mobile application that performs two functions. These functions have separate UIs and are easily toggled between using the button at the bottom of the UI. The first function has a text field where the user can write text. The user has the ability to generate a random color to use when writing in the text field by pressing a button. The app displays the RGB values of the color chosen in the format COLOR: xr, xg, xb where x is the value of the color on the red, green, and blue channel respectively. The app also displays the HTML color code for the generated color. The second function has a drawing panel and a color picker. The user has the option to select any color and touch the drawing panel to draw with the choosen color on the screen with their finger. The UI has a mechanism to clear the screen and save drawings in png file format.


**Operation Instructions:**

![](HW1ColorApp/Capture1.PNG)

Click on 'Change Color' button to generate a random color to write inside of the EditText object.

![](HW1ColorApp/Capture2.PNG)

To have the ability to draw, click on the 'Go to Drawing Panel' button.

![](HW1ColorApp/Capture3.PNG)
 
You have the ability to draw something inside of the panel. To choose a color to draw on the panel with, click the 'Pick Color' button. A dialog box will pop up which allows a user to select a unique color. Once you have the color you want, click on the 'select' option in the bottom right.

![](HW1ColorApp/Capture4.PNG)

There is also a 'custom' option in the bottom left of the dialog box.

![](HW1ColorApp/Capture5.PNG)

Once a color is selected you have the ability to draw with that color in the panel. 

![](HW1ColorApp/Capture6.PNG)

To clear the contents of the panel, click the 'Clear' button. To save the contents of the panel as a png file, click the 'Save' button. The saved image will appear in the images section of the file folder.

![](HW1ColorApp/Capture7.PNG)

To return to the first view, click on the 'Go to Change Color' button at the bottom of the UI. 


**Design of software:**
MainActivity class extends AppCompatActivity. It contains an EditText, TextView, and two Button objects. The button in the upper section of the UI, named 'Change Color' has a setOnClickListener which creates a random color through random rgb values. It then sets the text of the TextView with the format COLOR: xr, xg, xb #FFFFFF (where x is the value of the color on the red, green, and blue channel respectively and #FFFFFF is the HTML color code of the generated color. The button on the bottom of the UI, named 'Go to Drawing Panel' also has a setOnClickListener which changes the view to the DrawingPanelActivity. 

DrawingPanelActivity class extends AppCompatActivity and implements ColorPickerDialogListener. It contains four Buttons and a DrawView object. The DrawView object is implemented through an external library. It has three buttons that correlate with its usage. These buttons are 'Clear', 'Save', and 'Pick Color'. The 'Clear' button erases everything drawn on the DrawView object. The 'Save' button gets permission to read and write to external storage before saving the image. The 'Pick Color' button creates a ColorPickerDialog which is implemented through an external library. This dialog allows the user to select a color to draw with. Once the user hits 'select' the color they have chosen will be available to draw with on the DrawView object. At the bottom of the UI is the button which allows the user to return to the MainActivity.


**External libraries:**

- DrawView
Installation instructions: 

Add following line of code to build.gradle (Module.app): implementation 'com.byox.drawview:drawview:1.3.1'

Add permission requests in AndroidManifest.xml file:
<!-- if you need to find a path or read storage state -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<!-- if you need to save a draw as an image -->
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

Add DrawView to layout file:
<com.byox.drawview.views.DrawView
        android:id="@+id/draw_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:dv_draw_alpha="255"
        app:dv_draw_anti_alias="true"
        app:dv_draw_color="@color/colorAccent"
        app:dv_draw_corners="round"
        app:dv_draw_dither="true"
        app:dv_draw_enable_zoom="true"
        app:dv_draw_font_family="default_font"
        app:dv_draw_font_size="12"
        app:dv_draw_max_zoom_factor="8"
        app:dv_draw_mode="draw"
        app:dv_draw_style="stroke"
        app:dv_draw_tool="pen"
        app:dv_draw_width="4"
        app:dv_draw_zoomregion_maxscale="5"
        app:dv_draw_zoomregion_minscale="2"
        app:dv_draw_zoomregion_scale="4" />


- ColorPicker
Installation instructions:

Add following line of code to build.gradle (Module.app): implementation 'com.jaredrummler:colorpicker:1.0.2'

Create color picker dialog with next line of code:
ColorPickerDialog.newBuilder().setColor(color).show(activity);


