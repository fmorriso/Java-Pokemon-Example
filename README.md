# Pokemon Example
An example of using a URL to read and process a .csv file stored in DropBox.

## Tools Used

| Tool     |  Version |
|:---------|---------:|
| Java     | 23.0.2.0 |
| IntelliJ | 2024.3.3 |
| VSCode   |   1.98.0 |

## Change History

| Date       | Description                                                        |
|:-----------|:-------------------------------------------------------------------|
| 2025-03-03 | Initial creation                                                   |
| 2025-03-04 | Add ability to read from a text file publicly available on DropBox |

## References

* [Reading directly from a URL](https://docs.oracle.com/javase/tutorial/networking/urls/readingURL.html)
* [StateData2020-CDC-Census.csv](https://data.census.gov/)
* [CDC Data Sets](https://open.cdc.gov/data.html)

## Developer Notes

* [URL to StateData.csv](https://1drv.ms/u/s!Ash3pFpgn-Cnyr18zLwmbT6q_S0Psg?e=EQiwfQ)
* IMPORTANT NOTE: When reading a text file from DropBox, you MUST change the end of the generated
public link from `&dl=0` to `&dl=1`; otherwise, you get HTML instead of text.
        
