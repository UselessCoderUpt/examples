package com.examples.presentation.components

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.examples.domain.data.PawnItem
import java.util.*
import com.examples.R
import com.examples.ui.theme.ExamplesTheme
import dagger.hilt.android.qualifiers.ApplicationContext


@Composable
fun PawnItemCard(
    pawnItem: PawnItem,
    onItemClick: (PawnItem) -> Unit
) {
    Log.d("Rj CARD", "Inside card")
    Card(
        elevation = 6.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 7.dp)
        //.border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(30.dp))
    ) {
        val context = LocalContext.current
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .clickable { onPawnItemClick(context, pawnItem.MobileNo) }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.40f)
            ) {
                RowItem(R.string.lbl_Loan, pawnItem.LoanNo.toString())
                RowItem(R.string.lbl_Weight, "%.3f".format(pawnItem.Weight))
                RowItem(R.string.lbl_Amount, "%.0f".format(pawnItem.Amount))
                RowItem(R.string.lbl_Interest, "%.0f".format(pawnItem.RenewIntAmount))
                RowItem(R.string.lbl_Total, "%.0f".format(pawnItem.TotalAmount))
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "${pawnItem.CustomerName.uppercase()}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold,
                )
                RowItem(R.string.lbl_Mobile, pawnItem.MobileNo)
                RowItem(R.string.lbl_Place, pawnItem.Place)
                RowItem(R.string.lbl_ItemType, pawnItem.ItemType)
                RowItem(R.string.lbl_ItemName, pawnItem.ItemName)
            }
        }
    }
}

//@Composable
fun onPawnItemClick(context: Context, mobileNo: String) {
        //val context = LocalContext.current
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$mobileNo")
        context.startActivity(intent)
}

@Composable
fun RowItem(resourceId: Int, value: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(stringResource(resourceId))
            }
            append(value)
        }
    )
}

@Preview(
    name = "Light Mode",
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark Mode",
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun PreviewItemCard() {
    ExamplesTheme {
        val pawnitem = PawnItem(
            1111,
            Date(),
            "Theerthamalaijhghjghjghfbvnvn",
            "Siruvathur",
            "9976865070",
            "Stud",
            "G",
            2.100,
            105000.00,
            225.00,
            5225.0,
            2
        )
        PawnItemCard(pawnItem = pawnitem, onItemClick = { })
    }
}
