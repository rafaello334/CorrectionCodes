package pl.kodykorekcyjne.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.kodykorekcyjne.model.FileManager;

public class MainPaneController implements Initializable
{
	@FXML
	private Label error1Label;

	@FXML
	private Button calculateOutputButton;

	@FXML
	private TextField inputBit3;

	@FXML
	private Label input1Label;

	@FXML
	private Label receiver0;

	@FXML
	private Label receiver2;

	@FXML
	private Label receiver1;

	@FXML
	private Label receiver3;

	@FXML
	private TextField inputBit0;

	@FXML
	private TextField inputBit2;

	@FXML
	private TextField inputBit1;

	@FXML
	private Label input2Label;

	@FXML
	private Button resetButton;

	@FXML
	private Label input0Label;

	@FXML
	private Label input3Label;

	@FXML
	private Label error2Label;

	@FXML
	private Label outputBit2;

	@FXML
	private Label outputBit3;

	@FXML
	private Label outputBit0;

	@FXML
	private Label outputBit1;

	@FXML
	private Label error0Label;

	@FXML
	private TextField error1;

	@FXML
	private TextField error0;

	@FXML
	private Label error3Label;

	@FXML
	private TextField error3;

	@FXML
	private TextField error2;

	private File fileInput;
	private File fileOutput;

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		fileInput = new File("input.txt");
		fileOutput = new File("output.txt");
		configureButtonsAndInput();
	}

	private void configureButtonsAndInput()
	{
		try
		{
			String[] array = FileManager.readInputFromFile(fileInput).split(" ");
			inputBit0.setText(array[0]);
			inputBit1.setText(array[1]);
			inputBit2.setText(array[2]);
			inputBit3.setText(array[3]);

		} catch (IOException e)
		{
			e.printStackTrace();
		}

		resetButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				inputBit0.setText("");
				inputBit1.setText("");
				inputBit2.setText("");
				inputBit3.setText("");

				error0.setText("");
				error1.setText("");
				error2.setText("");
				error3.setText("");

				receiver0.setText("");
				receiver1.setText("");
				receiver2.setText("");
				receiver3.setText("");

				outputBit0.setText("");
				outputBit1.setText("");
				outputBit2.setText("");
				outputBit3.setText("");
			}
		});

		calculateOutputButton.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				if (checkInput())
				{
					transmissionChannel();
					receiver();
					writeToFile();
				}
			}

		});
	}

	private boolean checkInput()
	{
		if (!inputBit0.getText().matches("[0-1]"))
		{
			showErrorAlert(input0Label.getText());
			return false;
		}

		if (!inputBit1.getText().matches("[0-1]"))
		{
			showErrorAlert(input1Label.getText());
			return false;
		}

		if (!inputBit2.getText().matches("[0-1]"))
		{
			showErrorAlert(input2Label.getText());
			return false;
		}

		if (!inputBit3.getText().matches("[0-1]"))
		{
			showErrorAlert(input3Label.getText());
			return false;
		}
		if (!error0.getText().equals("")
				&& !(error0.getText().matches("[0-2]{0,3}") && checkForUnique(error0.getText())))
		{
			showErrorAlert(error0Label.getText());
			return false;
		}
		if (!error1.getText().equals("")
				&& !(error1.getText().matches("[0-2]{0,3}") && checkForUnique(error1.getText())))
		{
			showErrorAlert(error1Label.getText());
			return false;
		}

		if (!error2.getText().equals("")
				&& !(error2.getText().matches("[0-2]{0,3}") && checkForUnique(error2.getText())))
		{
			showErrorAlert(error2Label.getText());
			return false;
		}

		if (!error3.getText().equals("")
				&& !(error3.getText().matches("[0-2]{0,3}") && checkForUnique(error3.getText())))
		{
			showErrorAlert(error3Label.getText());
			return false;
		}

		return true;
	}

	private void showErrorAlert(String fieldName)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("B³¹d w danych wejœciowych!");
		alert.setContentText("Popraw dane w polu: " + fieldName);

		alert.showAndWait();
	}

	private void transmissionChannel()
	{
		StringBuilder output0 = new StringBuilder(inputBit0.getText() + inputBit0.getText() + inputBit0.getText());
		StringBuilder output1 = new StringBuilder(inputBit1.getText() + inputBit1.getText() + inputBit1.getText());
		StringBuilder output2 = new StringBuilder(inputBit2.getText() + inputBit2.getText() + inputBit2.getText());
		StringBuilder output3 = new StringBuilder(inputBit3.getText() + inputBit3.getText() + inputBit3.getText());

		if (error0.getText().equals(""))
		{
			receiver0.setText(output0.toString());
		} else
		{
			char error0Position[] = new char[error0.getText().length()];
			for (int i = 0; i < error0Position.length; i++)
			{
				error0Position[i] = error0.getText().charAt(i);
			}

			if (inputBit0.getText().equals("0"))
			{
				for (char error : error0Position)
				{
					if (error == '0')
						output0.setCharAt(0, '1');
					if (error == '1')
						output0.setCharAt(1, '1');
					if (error == '2')
						output0.setCharAt(2, '1');
				}
				receiver0.setText(output0.toString());
			} else
			{
				for (char error : error0Position)
				{
					if (error == '0')
						output0.setCharAt(0, '0');
					if (error == '1')
						output0.setCharAt(1, '0');
					if (error == '2')
						output0.setCharAt(2, '0');
				}
				receiver0.setText(output0.toString());
			}
		}

		if (error1.getText().equals(""))
		{
			receiver1.setText(output1.toString());
		} else
		{
			char error1Position[] = new char[error1.getText().length()];
			for (int i = 0; i < error1Position.length; i++)
			{
				error1Position[i] = error1.getText().charAt(i);
			}

			if (inputBit1.getText().equals("0"))
			{
				for (char error : error1Position)
				{
					if (error == '0')
						output1.setCharAt(0, '1');
					if (error == '1')
						output1.setCharAt(1, '1');
					if (error == '2')
						output1.setCharAt(2, '1');
				}
				receiver1.setText(output1.toString());
			} else
			{
				for (char error : error1Position)
				{
					if (error == '0')
						output1.setCharAt(0, '0');
					if (error == '1')
						output1.setCharAt(1, '0');
					if (error == '2')
						output1.setCharAt(2, '0');
				}
				receiver1.setText(output1.toString());
			}
		}

		if (error2.getText().equals(""))
		{
			receiver2.setText(output2.toString());
		} else
		{
			char error2Position[] = new char[error2.getText().length()];
			for (int i = 0; i < error2Position.length; i++)
			{
				error2Position[i] = error2.getText().charAt(i);
			}

			if (inputBit2.getText().equals("0"))
			{
				for (char error : error2Position)
				{
					if (error == '0')
						output2.setCharAt(0, '1');
					if (error == '1')
						output2.setCharAt(1, '1');
					if (error == '2')
						output2.setCharAt(2, '1');
				}
				receiver2.setText(output2.toString());
			} else
			{
				for (char error : error2Position)
				{
					if (error == '0')
						output2.setCharAt(0, '0');
					if (error == '1')
						output2.setCharAt(1, '0');
					if (error == '2')
						output2.setCharAt(2, '0');
				}
				receiver2.setText(output2.toString());
			}
		}

		if (error3.getText().equals(""))
		{
			receiver3.setText(output3.toString());
		} else
		{
			char error3Position[] = new char[error3.getText().length()];
			for (int i = 0; i < error3Position.length; i++)
			{
				error3Position[i] = error3.getText().charAt(i);
			}

			if (inputBit3.getText().equals("0"))
			{
				for (char error : error3Position)
				{
					if (error == '0')
						output3.setCharAt(0, '1');
					if (error == '1')
						output3.setCharAt(1, '1');
					if (error == '2')
						output3.setCharAt(2, '1');
				}
				receiver3.setText(output3.toString());
			} else
			{
				for (char error : error3Position)
				{
					if (error == '0')
						output3.setCharAt(0, '0');
					if (error == '1')
						output3.setCharAt(1, '0');
					if (error == '2')
						output3.setCharAt(2, '0');
				}
				receiver3.setText(output3.toString());
			}
		}
	}

	private void receiver()
	{
		int length0 = 0;
		int length1 = 0;
		int length2 = 0;
		int length3 = 0;

		for (int i = 0; i < 3; i++)
		{
			if (receiver0.getText().charAt(i) == '0')
			{
				length0++;
			}

			if (receiver1.getText().charAt(i) == '0')
			{
				length1++;
			}

			if (receiver2.getText().charAt(i) == '0')
			{
				length2++;
			}

			if (receiver3.getText().charAt(i) == '0')
			{
				length3++;
			}
		}

		calculateOutput(length0, outputBit0);
		calculateOutput(length1, outputBit1);
		calculateOutput(length2, outputBit2);
		calculateOutput(length3, outputBit3);
	}

	private void calculateOutput(int length, Label output)
	{
		if (length > 1)
		{
			output.setText("0");
		} else
		{
			output.setText("1");
		}
	}

	private boolean checkForUnique(String str)
	{
		HashSet<Character> unique = new HashSet<Character>();
		for (int i = 0; i < str.length(); i++)
		{
			unique.add(str.charAt(i));
		}
		if (unique.size() != str.length())
		{
			return false;
		}
		return true;
	}

	private void writeToFile()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("Raport:\n\n");
		sb.append("Dane wejœciowe: " + inputBit0.getText() + " " + inputBit1.getText() + " " + inputBit2.getText() + " "
				+ inputBit3.getText() + "\n");
		if (error0.getText().equals("") && error1.getText().equals("") && error2.getText().equals("")
				&& error3.getText().equals(""))
		{
			sb.append("B³êdy: brak\n");
		} else

		{
			sb.append("B³êdy: " + error0.getText() + " " + error1.getText() + " " + error2.getText() + " "
					+ error3.getText() + "\n");
		}
		sb.append("Odbiornik: " + receiver0.getText() + " " + receiver1.getText() + " " + receiver2.getText() + " "
				+ receiver3.getText() + "\n");
		sb.append("Dane wyjœciowe: " + outputBit0.getText() + " " + outputBit1.getText() + " " + outputBit2.getText()
				+ " " + outputBit3.getText() + "\n\n");
	
		FileManager.writeRaportToFile(fileOutput, sb);
	}
}
