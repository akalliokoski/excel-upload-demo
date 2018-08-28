import * as React from 'react';
import Dropzone from 'react-dropzone'
import * as request from 'superagent';

interface FileUploadProps {
    url: string;
    onUploadStart: () => void
    onUploadEnd: (err: any, res: any) => void;
}

interface FileUploadState {
}

class FileUpload extends React.Component<FileUploadProps, FileUploadState> {

    constructor(props: FileUploadProps) {
        super(props);
        this.onDrop = this.onDrop.bind(this);
    }

    render() {
        return (
        <div>
        <div className="file-upload-wrapper">
            <Dropzone
                multiple={false}
                onDropAccepted={this.props.onUploadStart}
                onDrop={this.onDrop}>
                <h4>Drop Excel files here</h4>
            </Dropzone>
        </div>
        </div>
        );
    }

    onDrop(acceptedFiles: Array<any>) {
        const { url } = this.props;
        const req = request.post(url);
        req.attach('file', acceptedFiles[0]);
        req.end(this.props.onUploadEnd);
    }
}

export default FileUpload;