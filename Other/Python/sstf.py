#Add impl
class SSTFSetting:
    def __init__(self, Name: str, Data: str):
        self.name = Name
        self.data = Data

class SSTF:
    @staticmethod
    def writesettings(path, settings):
        fp = open(path, "w")
        for i in settings:
            if(type(i) is SSTFSetting):
                fp.write(i.name+"│"+i.data)
        fp.close()

    @staticmethod
    def parsesettings(path):
        return
        #Finish later