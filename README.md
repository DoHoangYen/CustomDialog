# CustomDialog


+Enter Password

![alt tag](https://raw.githubusercontent.com/yenspkt/CustomDialog/master/Screenshot_20160924-154320%5B1%5D.png)

![alt tag](https://raw.githubusercontent.com/yenspkt/CustomDialog/master/Screenshot_20160924-154327%5B1%5D.png)

```javascript
new CustomDialogEnterText("Please Enter Text",size.x,MainActivity.this)
                        .setEnterText(getResources().getString(R.string.password))
                        .setUpPositiveButton(R.string.ok, getResources().getColor(R.color.green),
                                new CustomDialogEnterText.OnClickListener() {
                                    @Override
                                    public void onClick(View v, String content) {

                                    }
                                })
                        .setUpNegativeButton(R.string.cancel, Color.BLACK,
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                        .show();
```


+Enter text/number:

![alt tag](https://github.com/yenspkt/CustomDialog/blob/master/Screenshot_20160924-155154%5B1%5D.png)

```javascript
new CustomDialogEnterText("Please Enter Text",size.x,MainActivity.this)
                        .setEnterText(getResources().getString(R.string.password))
                        .setEnterHintPassword(false)
                        .setUpPositiveButton(R.string.ok, getResources().getColor(R.color.green),
                                new CustomDialogEnterText.OnClickListener() {
                                    @Override
                                    public void onClick(View v, String content) {

                                    }
                                })
                        .setUpNegativeButton(R.string.cancel, Color.BLACK,
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                    }
                                })
                        .show();
```

