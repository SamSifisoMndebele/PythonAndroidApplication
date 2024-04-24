import io
from matplotlib import pyplot as plt


def plot_table(data):
    plt.rcParams["figure.figsize"] = [7.00, 3.50]
    plt.rcParams["figure.autolayout"] = True
    fig, axs = plt.subplots(1, 1)
    columns = ("Column I", "Column II", "Column III")
    axs.axis('tight')
    axs.axis('off')
    axs.table(cellText=data, colLabels=columns, loc='center')
    image: bytes
    with io.BytesIO() as buffer:  # use buffer memory
        plt.savefig(buffer, format='png')
        buffer.seek(0)
        image = buffer.getvalue()
    return image
